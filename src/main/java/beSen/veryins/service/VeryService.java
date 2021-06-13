package beSen.veryins.service;

import beSen.mysql.BsMysql;
import beSen.veryins.model.Photo;
import cn.hutool.http.HttpRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;

@Service
public class VeryService {
    /**
     * 图片下载URL
     */
    private final String path = "https://www.veryins.com/user/post?next=%s&uid=%s";
    @Value("${nodes.next}")
    private String next;
    @Value("${nodes.uid}")
    private String uid;

    @Autowired
    private BsMysql bsMysql;

    /**
     * 根据类别查询图片信息
     *
     * @param category
     * @return
     * @throws Exception
     */
    public List<Photo> queryPhotoByCategory(String category) throws Exception {
        String sql = "select t.* from t_photo t where t.category = ? order by t.id desc limit 0,1";
        List<Photo> result = bsMysql.query(sql, Photo.class,category);
        return result;
    }

    /**
     * 请求URL
     *
     * @param category
     * @return
     * @throws Exception
     */
    public String requestUrl(String category) throws Exception {
        List<Photo> result = queryPhotoByCategory(category);
        if (result != null && result.size() > 0) {
            Photo photo = result.get(0);
            next = photo.getNext();
        }
        String param = String.format(Locale.ROOT, path, next, uid);
        String response = HttpRequest.post(param).body("").execute().body();
        return response;
    }
}
