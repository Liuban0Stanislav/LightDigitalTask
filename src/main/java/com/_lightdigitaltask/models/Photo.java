package com._lightdigitaltask.models;
import lombok.*;

import javax.persistence.*;

@Entity
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "photo")
public class Photo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private long fileSize;
    private String mediaType;

    @Lob
    private byte[] data;

    @Override
    public String toString() {
        return "PhotoEntity{" +
                "id=" + id +
                ", fileSize=" + fileSize +
                ", mediaType='" + mediaType + '\'' +
                ", data=" + (data != null ? "byte array is full" : "byte array is empty") +
                '}';
    }
}
