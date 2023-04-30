package com.gurus.mobility.entity.Accomodation;


import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "Accommodation_Images")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Image {

        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private Long id;
        private String name;
        private String type;
        private byte[] img;

    public Image(String name, String type,byte[] img) {
        this.name=name;
        this.type=type;
        this.img=img;
    }
}
