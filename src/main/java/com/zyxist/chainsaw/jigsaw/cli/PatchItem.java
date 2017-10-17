/*
 * Copyright 2017 the original author or authors.
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
package com.zyxist.chainsaw.jigsaw.cli;

import com.zyxist.chainsaw.jigsaw.JigsawFlags;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PatchItem implements ArgProducer {
	private final String patchedModule;
	private final List<String> patchingJars = new ArrayList<>();

	public PatchItem(String patchedModule) {
		this.patchedModule = patchedModule;
	}

	public PatchItem with(String jarFile) {
		this.patchingJars.add(jarFile);
		return this;
	}

	@Override
	public void toArgs(List<String> args) {
		if (!patchingJars.isEmpty()) {
			args.add(JigsawFlags.PATCH_MODULE);
			args.add(patchedModule+"="+patchingJars.stream().collect(Collectors.joining(":")));
		}
	}
}