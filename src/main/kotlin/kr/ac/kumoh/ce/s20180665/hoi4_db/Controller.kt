package kr.ac.kumoh.ce.s20180665.hoi4_db
/*20180665 안재모*/
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

/*해당하는 국가태그가 없을 경우 예외함수*/
class CountryNotFoundException(message: String) : RuntimeException(message)
/*해당하는 이념ID가 없을 경우 예외함수*/
class IdeologyNotFoundException(message: String) : RuntimeException(message)

@RestController
class Controller() {
    /*초기 화면*/
    @GetMapping("/")
    fun welcome(): String {
        return "Welcome to HOIKI4!"
    }
}
@RestController
class CountryController( val countryService: CountryService ){
    /*전체 조회*/
    @GetMapping("/country")
    fun countryList(): List<Country> {
        return countryService.getAllCountries()
    }
    /*태그 조회*/
    @GetMapping("/country/{tag}")
    fun getCountry(@PathVariable tag: String): ResponseEntity<Country> {
        val cty = countryService.findCountryByTag(tag)
        return ResponseEntity.ok(cty)
    }
}
@RestController
class LeaderController(val leaderService: LeaderService) {
    /*지도자 전체 조회*/
    @GetMapping("/leader")
    fun leaderList(): List<Leader> {
        return leaderService.getAllLeaders()
    }
    /*전체 지도자 정보 조회*/
    @GetMapping("/leader/info")
    fun getAllLeaderInfo(): List<LdInfoDTO> {
        return leaderService.getAllLeadersInfo()
    }
    /*국가에 해당하는 지도자 검색*/
    @GetMapping("/leader/{countryTag}&{ideologyId}")
    fun searchLeaderInfo(@PathVariable countryTag: String, @PathVariable ideologyId: Int): List<LdInfoDTO> {
        return leaderService.getLeaderInfo(countryTag, ideologyId)
    }
}
@RestController
class CommanderController(val commanderService: CommanderService) {
    /*사령관 전체 조회*/
    @GetMapping("/commander")
    fun commanderList(): List<Commander> {
        return commanderService.getAllCommanders()
    }
    /*모든 사령관 전체 정보 조회*/
    @GetMapping("commander/info")
    fun getAllCommanderInfo(): List<CmdInfoDTO> {
        return commanderService.getAllCmdsInfo()
    }
    /*국가의 사령관 정보 검색*/
    @GetMapping("commander/{countryTag}")
    fun searchCmdsInfo(@PathVariable countryTag: String): List<CmdInfoDTO> {
        return commanderService.getCmdsInfo(countryTag)
    }
}
@RestController
class IdeologyController(val ideologyService: IdeologyService) {
    /*전체 이념 조회*/
    @GetMapping("/ideology")
    fun ideologyList(): List<Ideology> {
        return ideologyService.getAllIdeology()
    }
}
/*예외함수 설정*/
@ControllerAdvice
class GlobalExceptionHandler {
    @ExceptionHandler(CountryNotFoundException::class)
    fun handleCountryNotFoundException(ex: CountryNotFoundException): ResponseEntity<String> {
        return ResponseEntity(ex.message, HttpStatus.NOT_FOUND)
    }
    @ExceptionHandler(IdeologyNotFoundException::class)
    fun handleIdeologyNotFoundException(ex: IdeologyNotFoundException): ResponseEntity<String> {
        return ResponseEntity(ex.message, HttpStatus.NOT_FOUND)
    }
}
