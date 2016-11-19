package com.mirhoseini.bragi.numbers.adapter.model;

/**
 * Created by Mohsen on 18/11/2016.
 */

/**
 * Model which holds all rows values including their types using {@link RecordTypes}
 */
public class NumberRecord {

    private final RecordTypes recordType;
    private final Number number;

    public NumberRecord(RecordTypes recordType, Number number) {
        this.recordType = recordType;
        this.number = number;
    }

    public int getRecordType() {
        return recordType.getValue();
    }

    public Number getNumber() {
        return number;
    }

    public enum RecordTypes {
        HEADER(0), ITEM(1);

        private final int value;

        RecordTypes(int value) {
            this.value = value;
        }

        public static RecordTypes fromValue(int value) {
            for (RecordTypes recordType : RecordTypes.values()) {
                if (recordType.getValue() == value)
                    return recordType;
            }

            return null;
        }

        public int getValue() {
            return value;
        }
    }


}
