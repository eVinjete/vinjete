package si.evinjete.vinjete;

import java.io.Serializable;
import java.util.Date;

public class Vinjeta implements Serializable {
    private Integer id;

    private Integer clientId;

    private String numberPlate;


    /*private Date timestamp;

    private byte[] content;



    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }*/
    public String getNumberPlate() {
        return numberPlate;
    }

    public void setNumberPlate(String numberPlate) {
        this.numberPlate = numberPlate;
    }
/*
    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }*/

}
