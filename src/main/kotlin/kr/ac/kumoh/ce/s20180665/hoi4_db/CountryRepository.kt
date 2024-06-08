package kr.ac.kumoh.ce.s20180665.hoi4_db

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface CountryRepository : JpaRepository<Country, String> {
    fun findByTag(tag: String): Optional<Country>
}