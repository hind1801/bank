package dao.daoFiles;

import dao.IDao;
import presentation.modele.Client;
import presentation.modele.Sexe;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

public class ClientDao implements IDao<Client, Long> {
    @Override
    public List<Client> findAll() {
        List<Client> clients = new ArrayList<>();


        try {
           List<String> lines = Files.readAllLines(FileBasePaths.CLIENT_TABLE, StandardCharsets.UTF_8);
            lines.remove(0);

            if(!lines.isEmpty())
                       clients=
                       lines
                               .stream()
                               .map(line->{
                                   Client cl = null;
                                   StringTokenizer st = new StringTokenizer(line, "\t");

                                   long     id              =   Long.parseLong(st.nextToken());
                                   String   nom             =   st.nextToken();
                                   String   prenom          =   st.nextToken();
                                   String   login           =   st.nextToken();
                                   String   pass            =   st.nextToken();
                                   String   cin             =   st.nextToken();
                                   String   email           =   st.nextToken();
                                   String   tel             =   st.nextToken();
                                   String   sexe            =   st.nextToken();

                                   Sexe sex = null;

                                   if(email.equalsIgnoreCase("NULL")) email ="";
                                   if(tel.equalsIgnoreCase("NULL")) tel ="";
                                   if(!sexe.equalsIgnoreCase("NULL")) {
                                       if(sexe.equalsIgnoreCase("HOMME")) sex = Sexe.HOMME;
                                       else sex = Sexe.FEMME;

                                   }

                                   cl = new Client(login, pass, nom, prenom, cin, email, tel, sex);
                                    cl.setId(id);
                                   return cl;
                               })
                               .collect(Collectors.toList());


        } catch (IOException e) {
           e.printStackTrace();
        }

        return clients;
    }
    @Override
    public Client findById(Long idClient) {
        return findAll().stream()
                .filter(client -> client.getId() == idClient)
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

    private long getIncrementedIdByIndexFile(Path path){
        String idStr = null;
        Long id = 1L;
        try {
                    idStr =  Files.readString(path);
                    id = Long.parseLong(idStr);
                    id ++;
        } catch (IOException e) {id = 1L;}

        try {
                Files.writeString(path, id.toString());
            }
        catch (IOException e) { e.printStackTrace();}

        return id;
    }
    @Override
    public Client save(Client client) {
        // Solution D'incrémentation 1
        Long id = getIncrementedId();
        // Solution D'incrémentation 2
        //   id = getIncrementedIdByIndexFile(FileBasePaths.INDEX_CLIENT);
        String clientStr =  id + "\t\t\t" +
                        client.getNom()+ "\t" +
                        client.getPrenom()+ "\t\t" +
                        client.getLogin()+ "\t\t" +
                        client.getMotDePasse()+ "\t\t" +
                        client.getCin()+ "\t\t\t" +
                        (client.getEmail()!=null&&client.getEmail().trim().length()!=0?client.getEmail():"NULL")+ "\t" +
                        (client.getTel()!=null&&client.getTel().trim().length()!=0?client.getTel():"NULL")+ "\t\t" +
                        (client.getSexe()!=null?client.getSexe():"NULL")+ "\t\tNULL\n" ;

        try {
            Files.writeString(FileBasePaths.CLIENT_TABLE, clientStr, StandardOpenOption.APPEND);
            System.out.println("Client n ° "+ id + " a été ajouté avec succès ^_^");
            client.setId(id);
            }
        catch (IOException e) { e.printStackTrace();}

        return client;
    }


    public Client saveWithID(Client client) {

        String clientStr =  client.getId() + "\t\t\t" +
                client.getNom()+ "\t" +
                client.getPrenom()+ "\t\t" +
                client.getLogin()+ "\t\t" +
                client.getMotDePasse()+ "\t\t" +
                client.getCin()+ "\t\t\t" +
                (client.getEmail()!=null&&client.getEmail().trim().length()!=0?client.getEmail():"NULL")+ "\t" +
                (client.getTel()!=null&&client.getTel().trim().length()!=0?client.getTel():"NULL")+ "\t\t" +
                (client.getSexe()!=null?client.getSexe():"NULL")+ "\t\tNULL\n" ;

        try {
            Files.writeString(FileBasePaths.CLIENT_TABLE, clientStr, StandardOpenOption.APPEND);
            System.out.println("Client n ° "+ client.getId() + " a été ajouté avec succès ^_^");
        }
        catch (IOException e) { e.printStackTrace();}

        return client;
    }
    @Override
    public List<Client> saveAll(List<Client> listeClients) {
        return
                listeClients
                            .stream()
                            .map(client -> save(client))
                            .collect(Collectors.toList());
    }

    public List<Client> saveAllWithIds(List<Client> listeClients) {
        return
                listeClients
                        .stream()
                        .map(client -> saveWithID(client))
                        .collect(Collectors.toList());
    }
    @Override
    public Client update(Client newClient) {

        List<Client> clientsUpdated =
                                findAll()
                                        .stream()
                                        .map(client -> {
                                                        if(client.getId() == newClient.getId())
                                                            return newClient;
                                                        else
                                                            return client;
                                                        })
                                                .collect(Collectors.toList());


        try { Files.deleteIfExists(FileBasePaths.INDEX_CLIENT);} catch (IOException e) {e.printStackTrace();}
        FileBasePaths.changeFile(FileBasePaths.CLIENT_TABLE, FileBasePaths.CLIENT_TABLE_HEADER);

        saveAll(clientsUpdated);

        return newClient;
    }

    @Override
    public Boolean delete(Client clientToDelete) {

        var clients = findAll();
        boolean deleted  =
                clients.remove(clientToDelete);

        if(deleted) {
                        FileBasePaths.changeFile(FileBasePaths.CLIENT_TABLE, FileBasePaths.CLIENT_TABLE_HEADER);
                        saveAllWithIds(clients);
                     }

        return deleted;
    }

    @Override
    public Boolean deleteById(Long idClient) {

        var clients = findAll();
        boolean deleted  =
                clients.remove(findById(idClient));

        if(deleted) {
           /* try {
                Files.deleteIfExists(FileBasePaths.INDEX_CLIENT);
            } catch (IOException e) {
                e.printStackTrace();
            }

            */
           FileBasePaths.changeFile(FileBasePaths.CLIENT_TABLE, FileBasePaths.CLIENT_TABLE_HEADER);
            saveAllWithIds(clients);
        }

        return deleted;

    }
    public List<Client> findByKeywordLike(String keyWord){
        List<Client> clients = findAll();
        return
        clients
                .stream()
                .filter(client ->
                                client.getId().toString().equals(keyWord) ||
                                client.getNom().toLowerCase().contains(keyWord.toLowerCase())    ||
                                client.getPrenom().toLowerCase().contains(keyWord.toLowerCase())    ||
                                client.getLogin().equals(keyWord)    ||
                                client.getMotDePasse().equals(keyWord)    ||
                                client.getCin().equalsIgnoreCase(keyWord)    ||
                                client.getEmail().equalsIgnoreCase(keyWord)    ||
                                client.getTel().equals(keyWord)    ||
                                client.getSexe().toString().toLowerCase().equalsIgnoreCase(keyWord)
                )
                .collect(Collectors.toList());
    }
    public Client findByLoginAndPassword(String login , String password) {
       return findAll().stream()
                .filter(c -> c.getLogin().equals(login) && c.getMotDePasse().equals(password))
                .findFirst()
                .orElse(null);
    }
    public Client findByCin(String cin)
    {
        return findAll()
                .stream()
                .filter(client -> client.getCin().equals(cin))
                .findFirst()
                .orElse(null);
    }

}
