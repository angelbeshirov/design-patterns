package design.creational;

/**
 * @author angel.beshirov
 */
class HeavyEntry {
    private String field1;
    private String field2;
    private String field3;
    private String field4;

    private String mfield1;
    private String mfield2;
    private String mfield3;
    private String mfield4;

    public void setField1(String field1) {
        this.field1 = field1;
    }

    public void setField2(String field2) {
        this.field2 = field2;
    }

    public void setField3(String field3) {
        this.field3 = field3;
    }

    public void setField4(String field4) {
        this.field4 = field4;
    }

    public String getField1() {
        return field1;
    }

    public String getField2() {
        return field2;
    }

    public String getField3() {
        return field3;
    }

    public String getField4() {
        return field4;
    }

    public String getMfield1() {
        return mfield1;
    }

    public String getMfield2() {
        return mfield2;
    }

    public String getMfield3() {
        return mfield3;
    }

    public String getMfield4() {
        return mfield4;
    }

    public static class HeavyEntryBuilder {
        private String mfield1;
        private String mfield2;
        private String mfield3;
        private String mfield4;

        public HeavyEntryBuilder() {
        }

        public HeavyEntryBuilder withMfield1(String mfield1) {
            this.mfield1 = mfield1;
            return this;
        }

        public HeavyEntryBuilder withMfield2(String mfield2) {
            this.mfield2 = mfield2;
            return this;
        }

        public HeavyEntryBuilder withMfield3(String mfield3) {
            this.mfield3 = mfield3;
            return this;
        }

        public HeavyEntryBuilder withMfield4(String mfield4) {
            this.mfield4 = mfield4;
            return this;
        }

        public HeavyEntry build() {
            HeavyEntry heavyEntry = new HeavyEntry();
            heavyEntry.mfield1 = this.mfield1;
            heavyEntry.mfield2 = this.mfield2;
            heavyEntry.mfield3 = this.mfield3;
            heavyEntry.mfield4 = this.mfield4;
            return heavyEntry;
        }
    }

    @Override
    public String toString() {
        return "HeavyEntry{" +
                "field1='" + field1 + '\'' +
                ", field2='" + field2 + '\'' +
                ", field3='" + field3 + '\'' +
                ", field4='" + field4 + '\'' +
                ", mfield1='" + mfield1 + '\'' +
                ", mfield2='" + mfield2 + '\'' +
                ", mfield3='" + mfield3 + '\'' +
                ", mfield4='" + mfield4 + '\'' +
                '}';
    }
}
public class Builder {

    public static void main(String... args) {
        HeavyEntry heavyEntry = new HeavyEntry.HeavyEntryBuilder()
                .withMfield1("test1")
                .withMfield2("test2")
                .withMfield3("test3")
                .withMfield4("test4").build();

        System.out.println(heavyEntry);
    }
}
