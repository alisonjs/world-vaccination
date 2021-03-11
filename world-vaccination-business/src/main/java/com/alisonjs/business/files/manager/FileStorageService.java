package com.alisonjs.business.files.manager;

import java.nio.file.Path;
import java.util.stream.Stream;

public interface FileStorageService<T, S> {

    public void init();

    public void save(T file);

    public S load(String filename);

    public void deleteAll();

    public Stream<Path> loadAll();

}
