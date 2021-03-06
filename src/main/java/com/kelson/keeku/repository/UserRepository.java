/*
 * Copyright 2010~2013 the original author or authors.
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

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kelson.keeku.domain.User;
/**
 * 
* @ClassName: UserRepository 
* @Description: 用户信息Dao
* @author Kelson 
* @date 2013-1-27 上午1:39:34 
* @version 1.0
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	
	public User findByUserName(String userName);

}
