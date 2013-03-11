/*
 * Copyright 2010~2013 keeku.co.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.kelson.keeku.repository;

import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.kelson.keeku.domain.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {

	@Query(value = "select p  from Post p where threadId = :threadId")
	Page<Post> findAll(@Param("threadId") Integer threadId, Pageable pageable);

	@Query(value="select max(floor) + 1 from Post p where threadId = ?1")
	int findMaxFloor(Integer floor);
	
	@Modifying
	@Query(value="update Post p set p.subject=?3,p.message=?4,p.lastUpdatedDate=?5,p.lastUpdatedByUserId=?6,p.isModified=1  where  p.threadId = ?1 and p.postId =?2")
	int editPost(Integer threadId,Integer postId,String subject,String message,Date currentTime,Integer operatorId);
	
/*	@Modifying
	@Query(value="update Post p set p.message=?3,p.lastUpdatedDate=?4,p.lastUpdatedByUserId=?5  where  p.threadId = ?1 and p.postId =?2")
	int editPost(Integer threadId,Integer postId,String message,Date currentTime,Integer operatorId);*/
	
	/*@Modifying
	@Query(value = "INSERT INTO t_post (postId, forumId, threadId, subject, authorUserName, createdDate, lastUpdatedBy, lastUpdatedDate, message,authorIp, floor) VALUES (3, 2, 1, 'xxxxxx', 'KelsonZhao', '2013-02-23 00:15:12', 'KelsonZhao', '2013-02-23 00:15:12', '<p>abc</p>','123.11',2)" ,nativeQuery=true)
	public int addPost();*/

}
