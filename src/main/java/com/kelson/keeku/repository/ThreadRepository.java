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
import org.springframework.stereotype.Repository;

import com.kelson.keeku.domain.Thread;

@Repository
public interface ThreadRepository extends JpaRepository<Thread, Integer> {
	
	Page<Thread> findByForumId(Integer forumId,Pageable pageable);
	
	@Modifying
	@Query(value="update Thread t set t.subject=?2,t.lastUpdatedDate=?3,t.lastUpdatedByUserId=?4  where  t.threadId = ?1")
	int editThread(Integer threadId,String subject,Date currentTime,Integer operatorId);
	
	@Modifying
	@Query(value="update Thread t set t.replies=?4,t.lastUpdatedDate=?2,t.lastUpdatedByUserId=?3  where  t.threadId = ?1")
	int updateThreadReplies(Integer threadId,Date currentTime,Integer operatorId,Integer count);
	

}
