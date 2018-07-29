package com.qfedu.domain.user;

public class UserDetailWithBLOBs extends UserDetail {
    private String address;

    private String idimage1;

    private String idimage2;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getIdimage1() {
        return idimage1;
    }

    public void setIdimage1(String idimage1) {
        this.idimage1 = idimage1 == null ? null : idimage1.trim();
    }

    public String getIdimage2() {
        return idimage2;
    }

    public void setIdimage2(String idimage2) {
        this.idimage2 = idimage2 == null ? null : idimage2.trim();
    }
}