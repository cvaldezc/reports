package icrperusa.interfaces.utils;

public interface IManagerFile {

    boolean existsFile(String filename, boolean fullpath);

    boolean existsFile(String filename);

    boolean upload(Object file);

    boolean delete(String filename, boolean fullpath);

    boolean delete(String filename);

    void readFile(String filename);

    void readConfig(String filename);

}
