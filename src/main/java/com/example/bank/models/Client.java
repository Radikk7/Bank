package com.example.bank.models;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Data
@Entity
public class Client implements UserDetails {
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private Long id;
        private String surname;
        private String name;
        private String patronymic;// Отчество
        private String username;
        private boolean active;
        private String password;
        @OneToMany(fetch =FetchType.EAGER)
        private List<Card> cards;
        @ElementCollection(targetClass = Role.class,fetch = FetchType.EAGER)//fetch это тип подгрузки
        @Enumerated(EnumType.STRING)// храним данные в базе в виде строки
        private Set<Role> roles;


        public Client() {
        }

        public Client(String surname, String name, String patronymic, String username, String password, List<Card> cards) {
                this.surname = surname;
                this.name = name;
                this.patronymic = patronymic;
                this.username = username;
                this.password = password;
                this.cards = cards;
        }

        public Client(String surname, String name, String patronymic) {
                this.surname = surname;
                this.name = name;
                this.patronymic = patronymic;
        }

        public Client(String surname, String name, String patronymic, String username, String password) {
                this.surname = surname;
                this.name = name;
                this.patronymic = patronymic;
                this.username = username;
                this.password = password;
        }

        public Client(Long id, String surname, String name, String patronymic, String username, String password) {
                this.id = id;
                this.surname = surname;
                this.name = name;
                this.patronymic = patronymic;
                this.username = username;
                this.password = password;
        }




        public Long getId() {
                return id;
        }

        public String getSurname() {
                return surname;
        }

        public void setSurname(String surname) {
                this.surname = surname;
        }

        public String getName() {
                return name;
        }

        public void setName(String name) {
                this.name = name;
        }

        public String getPatronymic() {
                return patronymic;
        }

        public void setPatronymic(String patronymic) {
                this.patronymic = patronymic;
        }

        public String getUsername() {
                return username;
        }

        @Override
        public boolean isAccountNonExpired() {
                return true;
        }

        @Override
        public boolean isAccountNonLocked() {
                return true;
        }

        @Override
        public boolean isCredentialsNonExpired() {
                return true;
        }

        @Override
        public boolean isEnabled() {
                return isActive();
        }

        public void setUsername(String username) {
                this.username = username;
        }

        public boolean isActive() {
                return active;
        }

        public void setActive(boolean active) {
                this.active = active;
        }

        public List<Card> getCards() {
                return cards;
        }

        public void setCards(List<Card> cards) {
                this.cards = cards;
        }

        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
                return getRoles();
        }

        public String getPassword() {
                return password;
        }

        public void setPassword(String password) {
                this.password = password;
        }



        public void setId(Long id) {
                this.id = id;
        }

}
