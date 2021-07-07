package org.zerock.j10.user.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sample")
public class SampleController {

    /**
     * 모든 사용자들이 볼 수 있도록 오픈된 URL
     * @return String[]
     */
    @GetMapping("/all")
    public String[] doAll(){
        return new String[]{"AAA","AAA","AAA"};
    }

    /**
     * 회원만 볼 수 있도록 오픈된 URL
     * @return String[]
     */
    @GetMapping("/member")
    public String[] doMember(){
        return new String[]{"BBB","BBB","BBB"};
    }

    /**
     * 관리자만 볼 수 있도록 오픈된 URL
     * @return String[]
     */
    @GetMapping("/admin")
    public String[] doAdmin(){
        return new String[]{"CCC","CCC","CCC"};
    }




}
