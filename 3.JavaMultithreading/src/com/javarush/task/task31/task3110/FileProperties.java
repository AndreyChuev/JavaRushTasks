package com.javarush.task.task31.task3110;

public class FileProperties {

    private final String name;
    private final long size;
    private final long compressedSize;
    private final int compressionMethod;

    public FileProperties(String name, long size, long compressedSize, int compressionMethod) {
        this.name = name;
        this.size = size;
        this.compressedSize = compressedSize;
        this.compressionMethod = compressionMethod;
    }

    public String getName() {
        return name;
    }

    public long getSize() {
        return size;
    }

    public long getCompressedSize() {
        return compressedSize;
    }

    public int getCompressionMethod() {
        return compressionMethod;
    }

    public long getCompressionRatio() {
        return 100 - ((compressedSize * 100) / size);
    }

    @Override
    public String toString() {
        if (size > 0) {
            return String.format("%s %d Kb (%d Kb) сжатие: %d%%",
                    name,
                    convertBytesToKilobytes(size),
                    convertBytesToKilobytes(compressedSize),
                    getCompressionRatio());
        } else {
            return name;
        }
    }

    private long convertBytesToKilobytes(long bytes) {
        return bytes / 1024;
    }
}
