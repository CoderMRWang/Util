package com.wanghaotian.example.utils;



import com.wanghaotian.example.object.Item;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.common.bytes.BytesReference;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.common.xcontent.XContentParser;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.elasticsearch.ElasticsearchAutoConfiguration;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.Criteria;
import org.springframework.data.elasticsearch.core.query.CriteriaQuery;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

/**
 * author;Wanghaotian
 * data:2020/3/30 0030
 */

@ConditionalOnClass(ElasticsearchAutoConfiguration.class)
public class ElasticsearchUtils {
    @Resource
    private ElasticsearchTemplate template;

    public static void main(String[] args) {


    }

    public boolean initQueryObject(Class clazz) throws ExecutionException, InterruptedException {
        return initQueryObject(clazz, null);
    }

    public boolean initQueryObject(Class clazz, Object setting) throws ExecutionException, InterruptedException {
        return template.putMapping(clazz) && template.createIndex(clazz, setting);
    }

    public boolean addObject(List list, String idParam, Class clazz) throws NoSuchFieldException, IllegalAccessException {
        List<IndexQuery> queries = new ArrayList<>();
        for (Object o : list) {
            IndexQuery indexQuery = new IndexQuery();
            indexQuery.setObject(queries);
            Field field = clazz.getDeclaredField(idParam);
            if (!field.isAccessible()) {
                field.setAccessible(true);
            }
            indexQuery.setId(clazz.getDeclaredField(idParam).get(o) + "");
            queries.add(indexQuery);
        }
        template.bulkIndex(queries);
        return true;
    }

    public <T> List<T> queryForList(Class<T> clazz) {
        return queryForList(clazz, null, null, true);
    }

    public <T> List<T> queryForList(Class<T> clazz, String[] andParams, String[] orParams, boolean isTrue) {
        Criteria criteria = new Criteria();
        List<Criteria> criteriaList = new ArrayList<>();
        for (String and : andParams) {
            criteria.and(and);
        }
        for (String or : orParams) {
            criteria.or(or);
        }
        if (!isTrue) {
            criteria.not();
        }
        return template.queryForList(new CriteriaQuery(criteria), clazz);
    }



    @RequestMapping("/1")
    public void Test1() throws ExecutionException, InterruptedException, NoSuchFieldException, IllegalAccessException {
        ElasticsearchUtils elasticsearchUtils=new ElasticsearchUtils();
        elasticsearchUtils.initQueryObject(Item.class);
        List<Item> arraylist=new ArrayList<>();
        for (Long i=0L;i<10;i++)
        {
            Item item=new Item();
            item.setId(i);
            item.setPrice(100.02);
            arraylist.add(item);
        }
            elasticsearchUtils.addObject(arraylist,"id",Item.class);
        System.out.println("添加完成");

    }



}
