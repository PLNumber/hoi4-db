package kr.ac.kumoh.ce.s20180665.hoi4_db
import org.springframework.stereotype.Service
/*20180665 안재모*/
@Service
class CountryService (val countryRepository: CountryRepository) {
    /*모든 국가 조회 시*/
    fun getAllCountries(): List<Country> {
        return countryRepository.findAll() //모든 국가 리스트를 반환
    }
    /*태그로 검색 시*/
    fun findCountryByTag(countryTag: String): Country {
        if (!countryRepository.existsById(countryTag)) {    //해당하는 국가태그가 없을 경우
            throw CountryNotFoundException("존재하지 않는 국가입니다: $countryTag")    //오류매세지 출력
        }
        return countryRepository.findByTag(countryTag)  //countryTag에 해당하는 Country를 반환
    }
}
@Service
class LeaderService (
    val leaderRepository: LeaderRepository,
    val countryRepository: CountryRepository,
    val ideologyRepository: IdeologyRepository) {
    /*모든 지도자 테이블을 조회시*/
    fun getAllLeaders(): List<Leader> {
        return leaderRepository.findAll()   //모든 테이블 리스트를 반환
    }
    /*모든 지도자 정보를 조회시*/
    fun getAllLeadersInfo(): List<LdInfoDTO> {
        return leaderRepository.getAllLdInfo()  //모든 정보들의 리스트를 반환
    }
    /*조건에 맞는 지도자 정보를 조회시*/
    fun getLeaderInfo(countryTag: String, ideologyId: Int): List<LdInfoDTO> {
        if (!countryRepository.existsById(countryTag)) {    //해당하는 국가 태그가 없을경우
            throw CountryNotFoundException("존재하지 않는 국가입니다: $countryTag")
        }
        if (!ideologyRepository.ifIdeologyNull(countryTag, ideologyId)) { //해당하는 이념ID가 아닐 경우
            throw IdeologyNotFoundException("이 지도자는 이 이념이 아닙니다: $ideologyId")
        }
        return leaderRepository.getLdInfo(countryTag, ideologyId)
    }
}
@Service
class CommanderService (
    val commanderRepository: CommanderRepository,
    val countryRepository: CountryRepository) {
    /*모든 사령관 테이블을 조회시*/
    fun getAllCommanders(): List<Commander> {
        return commanderRepository.findAll()    //모든 사령관 리스트를 반환
    }
    /*모든 사령관 정보를 조회시*/
    fun getAllCmdsInfo(): List<CmdInfoDTO> {
        return commanderRepository.getCmdInfo() //모든 사령관 정보를 반환
    }
    /*조건에 맞는 사령관 정보를 조회시*/
    fun getCmdsInfo(countryTag: String): List<CmdInfoDTO> {
        if (!countryRepository.existsById(countryTag)) {    //해당하는 국가태그가 없을 시
            throw CountryNotFoundException("존재하지 않는 국가입니다: $countryTag")    //오류매세지
        }
        return commanderRepository.getCmdInfo(countryTag)   //국가태그에 해당하는 정보의 리스트를 반환
    }
}
@Service
class IdeologyService (val ideologyRepository: IdeologyRepository) {
    /*모든 이념 테이블을 조회시*/
    fun getAllIdeology(): List<Ideology> {
        return ideologyRepository.findAll() //모든 이념 테이블을 반환
    }
}
