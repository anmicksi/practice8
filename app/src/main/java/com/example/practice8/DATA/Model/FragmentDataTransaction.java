package com.example.practice8.DATA.Model;

public class FragmentDataTransaction {
    private String dataFromFirstToSecond;
    public FragmentDataTransaction() {
        this.dataFromFirstToSecond = "Переданные данные с экрана 1";
    }

    public FragmentDataTransaction(String dataFromFirstToSecond, String dataFromSecondToThird) {
        this.dataFromFirstToSecond = dataFromFirstToSecond;
    }

    public void setDataFromFirstToSecond(String dataFromFirstToSecond) {
        this.dataFromFirstToSecond = dataFromFirstToSecond;
    }

    public String getDataFromFirstToSecond() {
        return dataFromFirstToSecond;
    }

}
