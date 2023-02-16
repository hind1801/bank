package dao.daoFiles;

import dao.IDao;
import presentation.modele.Log;
import presentation.modele.TypeLog;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

public class LogDao implements IDao<Log,Long> {
    @Override
    public List<Log> findAll() {
        List<Log> logs = new ArrayList<>();


        try {
            List<String> lines = Files.readAllLines(FileBasePaths.LOGS_TABLE, StandardCharsets.UTF_8);
            lines.remove(0);

            if(!lines.isEmpty())
                logs=
                        lines
                                .stream()
                                .map(line->{
                                    Log cl = null;
                                    StringTokenizer st = new StringTokenizer(line, "\t");

                                    long     id              =   Long.parseLong(st.nextToken());
                                    LocalDate date    =  LocalDate.parse(st.nextToken());
                                    LocalTime time    =  LocalTime.parse(st.nextToken());
                                    String   typeLog  =  st.nextToken();
                                    String message    =  st.nextToken();
                                    Long   id_Compte  = Long.parseLong(st.nextToken());
                                        TypeLog t =null;
                                    if(!typeLog.equalsIgnoreCase("NULL")) {
                                        if(typeLog.equalsIgnoreCase("VIREMENT")) t = TypeLog.VIREMENT;
                                        else if(typeLog.equalsIgnoreCase("RETRAIT"))t = TypeLog.RETRAIT;
                                        else if(typeLog.equalsIgnoreCase("VERSEMENT"))t = TypeLog.VERSEMENT;
                                        else  t= TypeLog.CREATION;

                                    }

                                    cl = new Log(date, time, t, message, new AccountDao().findById(id_Compte));
                                    cl.setId(id);
                                    return cl;
                                })
                                .collect(Collectors.toList());


        } catch (IOException e) {
            e.printStackTrace();
        }

        return logs;
    }

    @Override
    public Log findById(Long idLog) {
        return findAll().stream()
                .filter(log -> log.getId() == idLog)
                .findFirst()
                .orElse(null);

    }

    private long getIncrementedId(){

        var clientList = findAll();

        var maxId = 1L;

        if(!clientList.isEmpty())
        {
            maxId = findAll().stream().map(client -> client.getId()).max((id1,id2)-> id1.compareTo(id2)).get();
            maxId++;
        }

        return maxId;
    }


    @Override
    public Log save(Log log) {
        Long id = getIncrementedId();
        String clientStr =  id + "\t\t\t" +
                log.getDate()+ "\t\t\t" +
                log.getTime()+ "\t\t\t" +
                log.getType()+ "\t\t\t" +
                log.getMessage()+ "\t\t\t" +
                log.getCompte().getNumeroCompte()+ "\n" ;

        try {
            Files.writeString(FileBasePaths.LOGS_TABLE, clientStr, StandardOpenOption.APPEND);
            log.setId(id);
        }
        catch (IOException e) { e.printStackTrace();}

        return log;
    }

    @Override
    public List<Log> saveAll(List<Log> liste) {
        return
                liste
                        .stream()
                        .map(log -> save(log))
                        .collect(Collectors.toList());
    }

    @Override
    public Log update(Log log) {

        List<Log> logUpdated =
                findAll()
                        .stream()
                        .map(client -> {
                            if(client.getId() == log.getId())
                                return log;
                            else
                                return client;
                        })
                        .collect(Collectors.toList());

        FileBasePaths.changeFile(FileBasePaths.LOGS_TABLE, FileBasePaths.LOGS_TABLE_HEADER);

        saveAll(logUpdated);

        return log;
    }

    @Override
    public Boolean delete(Log log) {
        var logs = findAll();
        boolean deleted  =
                logs.remove(log);

        if(deleted) {
            FileBasePaths.changeFile(FileBasePaths.LOGS_TABLE, FileBasePaths.LOGS_TABLE_HEADER);
            saveAll(logs);
        }

        return deleted;
    }

    @Override
    public Boolean deleteById(Long id_log) {
        var logs = findAll();
        boolean deleted  =
                logs.remove(findById(id_log));

        if(deleted) {


            FileBasePaths.changeFile(FileBasePaths.CLIENT_TABLE, FileBasePaths.CLIENT_TABLE_HEADER);
            saveAll(logs);
        }

        return deleted;
    }
    public List<Log> findByIdAccount(Long num)
    {
        return findAll()
                .stream()
                .filter(l->l.getCompte().getNumeroCompte() == num )
                .toList();
    }
    public List<Log> findByKeywordLike(String keyWord){

        List<Log> logs = findAll();

        return
                logs
                        .stream()
                        .filter(log ->
                                log.getId().toString().equals(keyWord) ||
                                        log.getType().toString().toLowerCase().equals(keyWord.toLowerCase())    ||
                                        log.getMessage().toLowerCase().contains(keyWord.toLowerCase())   ||
                                        log.getDate().toString().split("\\.")[0].substring(0, 5).equals(keyWord.substring(0, 5))    ||
                                        log.getMessage().toLowerCase().contains(keyWord.toLowerCase())    ||
                                        log.getCompte().getNumeroCompte().equals(keyWord)
                        )
                        .collect(Collectors.toList());

    }
}
