package com.example.hardyboyz.dayeon.domain.profile.repository;

import com.example.hardyboyz.dayeon.domain.profile.entity.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Long> {

}
