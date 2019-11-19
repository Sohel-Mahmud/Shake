package com.octoriz.sohel.saarc.Dao;

import com.octoriz.sohel.saarc.Entity.Country;
import com.octoriz.sohel.saarc.Entity.Name;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface ShakeDao {

    @Insert
    void insertName(Name name);

    @Insert
    void insertCountry(Country country);

    @Query("SELECT * FROM name_table WHERE country_id = :countryId")
    public LiveData<List<Name>> getAllNamesById(int countryId);

    @Query("SELECT * FROM country_table")
    public LiveData<List<Country>> getAllCountry();

}