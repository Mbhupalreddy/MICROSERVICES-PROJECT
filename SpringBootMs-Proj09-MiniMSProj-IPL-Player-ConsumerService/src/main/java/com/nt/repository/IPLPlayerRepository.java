package com.nt.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nt.entity.IPLPlayer;

public interface IPLPlayerRepository extends JpaRepository<IPLPlayer, Integer> {

}
