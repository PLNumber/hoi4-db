package kr.ac.kumoh.ce.s20180665.hoi4_db
/*20180665 안재모*/

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface CountryRepository : JpaRepository<Country, String> {
    @Query("""
        select cz
        from Country c
        where c.tag = :countryTag
    """)    //해당하는 국가태그에 맞는 조건으로 Query 생성
    fun findByTag(@Param("countryTag") countryTag: String): Country
}

@Repository
interface LeaderRepository : JpaRepository<Leader, LeaderId> {
    @Query("""
        select new kr.ac.kumoh.ce.s20180665.hoi4_db.LdInfoDTO(
        c.tag, c.name, c.image,
        i.name,
        l.name, l.portrait)
        from Country c left join Leader l
            on c.tag = l.country.tag
        left join Ideology i
            on l.ideology.id = i.id
    """) //조인은 2번 사용
    fun getAllLdInfo(): List<LdInfoDTO> //조인

    @Query("""
        select new kr.ac.kumoh.ce.s20180665.hoi4_db.LdInfoDTO(
        c.tag, c.name, c.image,
        i.name,
        l.name, l.portrait)
        from Country c left join Leader l
            on c.tag = l.country.tag
        left join Ideology i
            on l.ideology.id = i.id
        where c.tag = :countryTag and l.ideology.id = :ideologyId
    """)    //조인을 2번 사용 및 해당하는 국가태그, 이념ID에 맞는 조건으로 쿼리를 반환
    fun getLdInfo(@Param("countryTag") countryTag: String, @Param("ideologyId") ideologyId: Int): List<LdInfoDTO>

}

@Repository
interface CommanderRepository : JpaRepository<Commander, Int> {
    @Query("""
        select new kr.ac.kumoh.ce.s20180665.hoi4_db.CmdInfoDTO(
            c.tag, c.name, c.image,
            co.name, co.atk, co.def, co.pln, co.log)
        from Country c left join Commander co
            on c.tag = co.country.tag
    """) //조인은 1번 사용
    fun getCmdInfo(): List<CmdInfoDTO>

    @Query("""
        select new kr.ac.kumoh.ce.s20180665.hoi4_db.CmdInfoDTO(
            c.tag, c.name, c.image,
            co.name, co.atk, co.def, co.pln, co.log)
        from Country c left join Commander co
            on c.tag = co.country.tag
        where c.tag = :countryTag
    """) //조인은 1번 사용 및 국가태그에 해당하는 쿼리 사용
    fun getCmdInfo(@Param("countryTag") countryTag: String): List<CmdInfoDTO>
}

@Repository
interface IdeologyRepository : JpaRepository<Ideology, Int> {
    /*해당하는 이념 ID가 없을 경우 (즉 0)*/
    @Query("select count(l) > 0 from Leader l where l.country.tag = :countryTag and l.ideology.id = :ideologyId")
    fun ifIdeologyNull(@Param("countryTag") countryTag: String, @Param("ideologyId") ideologyId: Int): Boolean
}