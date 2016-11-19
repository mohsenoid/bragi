package net.bragi.foo.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mohsen on 18/11/2016.
 */

public class NumbersResponse {

    @SerializedName("numbers")
    @Expose
    private List<Integer> numbers = new ArrayList<>();

    public NumbersResponse(List<Integer> numbers) {
        this.numbers = numbers;
    }

    public List<Integer> getNumbers() {
        return numbers;
    }
}
