package dao.daoFiles;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public interface FileBasePaths {
    Path FILEBASEFOLDER = Paths.get("myFileBase");
    Path CLIENT_TABLE = Paths.get(FILEBASEFOLDER.toString(), "clients.txt");
    Path INDEX_CLIENT = Paths.get(FILEBASEFOLDER.toString(),"clientsLastIndex.txt");
    Path ACCOUNT_TABLE = Paths.get(FILEBASEFOLDER.toString(), "accounts.txt");
    Path LOGS_TABLE = Paths.get(FILEBASEFOLDER.toString(), "Logs.txt");



     private static void createFileOrDirectory(Path path, boolean isFile, String header) {


        if (!isFile) {
            if (!path.toFile().exists())
                    {
                            path.toFile().mkdir();
                            System.out.println(path.getFileName() + " a été créé avec succès");
                    }
            else            System.out.println(path.getFileName() + " existe déjà");

        }
        else {
            if (!path.toFile().exists()) {
                try {
                            path.toFile().createNewFile();
                            Files.writeString(path, header);
                            System.out.println(path.getFileName() + " a été créé avec succès");

                    }       catch (IOException e) { e.printStackTrace();}
            }
            else            System.out.println(path.getFileName() + " existe déjà");

        }

    }
     static void changeFile(Path path, String header) {

            if (path.toFile().exists()) {
                try {
                    path.toFile().delete();
                    path.toFile().createNewFile();
                    Files.writeString(path, header);
                    System.out.println(path.getFileName() + " a été changé avec succès");

                }       catch (IOException e) { e.printStackTrace();}
            }
            else            System.out.println(path.getFileName() + " n'existe pas");



    }
     static void createFileBase() {

        createFileOrDirectory(FILEBASEFOLDER,       false,"NULL");
        createFileOrDirectory(CLIENT_TABLE,         true, CLIENT_TABLE_HEADER);
        createFileOrDirectory(ACCOUNT_TABLE,        true, ACCOUNT_TABLE_HEADER);
        createFileOrDirectory(LOGS_TABLE,  true, LOGS_TABLE_HEADER);

    }
    String CLIENT_TABLE_HEADER  =  "ID\t\t\tNOM\t\t\tPRENOM\t\t\tLOGIN\t\t\tMOT DE PASS\t\t\tCIN\t\t\tEMAIL\t\t\tTELEPHONE\t\t\tSEXE\t\t\tID_AGENCE\n";
    String ACCOUNT_TABLE_HEADER = "ID\t\t\tDATE_CREATION\t\t\tSOLDE\t\t\tID_CLIENT\n";
    String LOGS_TABLE_HEADER  = "ID\t\t\tDATE\t\t\tTIME\t\t\tTYPE\t\t\tMESSAGE\t\t\tID_COMPTE\n";

}



