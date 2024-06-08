package kr.ac.kumoh.ce.s20180665.hoi4_db

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class CountryService @Autowired constructor(val repository: CountryRepository) {
    fun getAllCountries(): List<Country> {
        return repository.findAll()
    }

    fun findCountryByTag(tag: String): Country {
        return repository.findByTag(tag).orElseThrow {
            RuntimeException("Country Not Found with Tag : $tag")
        }
    }
}