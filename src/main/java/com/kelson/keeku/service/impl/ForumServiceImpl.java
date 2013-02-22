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
package com.kelson.keeku.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.kelson.keeku.domain.Thread;
import com.kelson.keeku.repository.ForumRepository;
import com.kelson.keeku.repository.PostRepository;
import com.kelson.keeku.repository.ThreadRepository;
import com.kelson.keeku.service.ForumService;

@Service
public class ForumServiceImpl implements ForumService{
	
	@Autowired
	ForumRepository fr;
	
	@Autowired
	ThreadRepository tr;
	
	@Autowired
	PostRepository pr;

	@Override
	public Page<Thread>  listThreads(Pageable pageable) {
		return tr.findAll(pageable);
	}

}
