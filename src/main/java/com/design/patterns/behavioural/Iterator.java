package design.behavioural;

import java.io.*;

/**
 *
 * This pattern is used to get a way to access the elements of a collection object in sequential manner without
 * any need to know its underlying representation.
 *
 *
 * @author angel.beshirov
 */

interface Entry<T> {
    T getData();
}

interface Repository<T extends Entry<?>> {
    Iterator<T> getIterator();
}

interface Iterator<T extends Entry<?>> {
    T getNext();

    boolean hasNext();
}

class LogEntry implements Entry<String> {

    private String row;

    public LogEntry(String row) {
        this.row = row;
    }

    @Override
    public String getData() {
        return row;
    }

    @Override
    public String toString() {
        return "LogEntry{" +
                "row='" + row + '\'' +
                '}';
    }
}

class LogRepository implements Repository<LogEntry>, Closeable {

    private final BufferedReader br;

    public LogRepository(String filename) throws IOException {
        this.br = new BufferedReader(new InputStreamReader(new FileInputStream(filename)));
    }

    @Override
    public Iterator<LogEntry> getIterator() {
        return new LogIterator(br);
    }

    @Override
    public void close() throws IOException {
        br.close();
    }
}

class LogIterator implements Iterator<LogEntry> {
    private final BufferedReader br;
    private String nextLine;

    public LogIterator(BufferedReader br) {
        this.br = br;
        try {
            this.nextLine = br.readLine();
        } catch (IOException e) {
            this.nextLine = null;
        }
    }

    @Override
    public LogEntry getNext() {
        if (hasNext()) {
            String oldLine = nextLine;
            update();
            return new LogEntry(oldLine);
        } else {
            return null;
        }
    }

    @Override
    public boolean hasNext() {
        return nextLine != null;
    }

    private void update() {
        try {
            this.nextLine = br.readLine();
        } catch (IOException e) {
            this.nextLine = null;
        }
    }
}

class DriverIterator {
    public static void main(String... args) {
        try {
            Repository<LogEntry> repo = new LogRepository("src//main//resources//behavioural//apache_logs.txt");
            Iterator<LogEntry> iter = repo.getIterator();

            while(iter.hasNext()) {
                System.out.println(iter.getNext());
            }


        } catch (IOException e) {
            System.err.println("Error while opening the file" + e.getMessage());
        }
    }
}
