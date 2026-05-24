package com.example.lightcrew.Repositories;

import com.example.lightcrew.Model.TeamMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeamMembersRepository extends JpaRepository<TeamMember, Long> {
    List<TeamMember> findAllTeamMembers();


}
