package dao.daoFiles;

import dao.IDao;
import presentation.modele.Compte;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

public class AccountDao  implements IDao<Compte, Long> {
    @Override
    public List<Compte> findAll() {
        List<Compte> comptes = new ArrayList<>();
        try {
            List<String> lines = Files.readAllLines(FileBasePaths.ACCOUNT_TABLE,StandardCharsets.UTF_8);
            lines.remove(0);
            if(!lines.isEmpty())
            {
                comptes =
                        lines
                                .stream()
                                .map(line ->
                                {
                                    Compte c = null;
                                    StringTokenizer st = new StringTokenizer(line , "\t");
                                    Long  num    =   Long.parseLong(st.nextToken());
                                    LocalDate datecreation   =   LocalDate.parse(st.nextToken());
                                    Double  solde    =   Double.parseDouble(st.nextToken());
                                    long  id        =   Long.parseLong(st.nextToken());
                                    ClientDao cd = new ClientDao();
                                    c = new Compte();
                                    c.setDateCreation(datecreation);
                                    c.setNumeroCompte(num);
                                    c.setPropriétaire(cd.findById(id));
                                    c.setSolde(solde);
                                    return c;

                                })
                                .collect(Collectors.toList());
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }


        return comptes;
    }
    @Override
    public Compte findById(Long numCompte) {
        return findAll().stream()
                .filter(compte -> compte.getNumeroCompte() == numCompte)
                .findFirst()
                .orElse(null);
    }
    private long getIncrementedId(){

        List<Compte> comptes = findAll();

        var maxId = 1L;

        if(!comptes.isEmpty())
        {
            maxId = findAll().stream().map(client -> client.getNumeroCompte()).max((id1,id2)-> id1.compareTo(id2)).get();
            maxId++;
        }

        return maxId;
    }
    @Override
    public Compte save(Compte compte) {
        Long id = getIncrementedId();
    String compteStr  =   id + "\t\t\t"+
                    compte.getDateCreation() + "\t\t\t"+
                    compte.getSolde()+ "\t\t\t"+
                    compte.getPropriétaire().getId()+"\n";
    try
    {
        Files.writeString(FileBasePaths.ACCOUNT_TABLE , compteStr , StandardOpenOption.APPEND);
        System.out.println("Compte n ° "+ id + " a été ajouté avec succès ^_^");
    }
    catch (IOException e)
    {
        e.printStackTrace();
    }


return compte;
    }
    @Override
    public List<Compte> saveAll(List<Compte> liste) {
        return liste.stream()
                .map(compte -> save(compte))
                .collect(Collectors.toList());
    }
    @Override
    public Compte update(Compte compte) {

        List<Compte> clientsUpdated =
                findAll()
                        .stream()
                        .map(client -> {
                            if(client.getNumeroCompte() == compte.getNumeroCompte())
                                return compte;
                            else
                                return client;
                        })
                        .collect(Collectors.toList());

        FileBasePaths.changeFile(FileBasePaths.ACCOUNT_TABLE, FileBasePaths.ACCOUNT_TABLE_HEADER);

        saveAll(clientsUpdated);
        return compte;

    }

    @Override
    public Boolean delete(Compte compte) {
        var clients = findAll();
        boolean deleted  =
                clients.remove(compte);

        if(deleted) {
            FileBasePaths.changeFile(FileBasePaths.ACCOUNT_TABLE, FileBasePaths.ACCOUNT_TABLE_HEADER);
            saveAll(clients);
        }

        return deleted;
    }
    @Override
    public Boolean deleteById(Long num) {
        var clients = findAll();
        boolean deleted  =
                clients.remove(findById(num));
        if(deleted) {
            FileBasePaths.changeFile(FileBasePaths.ACCOUNT_TABLE, FileBasePaths.ACCOUNT_TABLE_HEADER);
            saveAll(clients);
        }
        return deleted;
    }
    public List<Compte> findByIdClient (Long IdClient) {
        return findAll()
                .stream()
                .filter(compte -> compte.getPropriétaire().getId() == IdClient)
                .toList();
    }

    public List<Compte> findByKeywordLike(String keyWord){

        List<Compte> comptes = findAll();
        return
                comptes
                        .stream()
                        .filter(compte ->
                                compte.getNumeroCompte().toString().equals(keyWord) ||
                                        compte.getPropriétaire().getNomComplet().toLowerCase().contains(keyWord.toLowerCase())
                        )
                        .collect(Collectors.toList());
    }



}
