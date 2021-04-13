package dao;

import java.util.HashMap;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import bean.Member;

@Component("mdao")
public class MemberDao {
    private final String namespace = "NsMember.";

    @Autowired
    SqlSessionTemplate abcd;

	public Member SelectById(String id, String password) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("password", password);
		return this.abcd.selectOne(namespace + "SelectById", map);
	}

	public int SiginInData(Member xxx) {
		return this.abcd.insert(namespace + "SiginInData", xxx);
	}

	public int IdCheck(String mid) {
		return this.abcd.selectOne(namespace+"IdCheck",mid);
	}

}
