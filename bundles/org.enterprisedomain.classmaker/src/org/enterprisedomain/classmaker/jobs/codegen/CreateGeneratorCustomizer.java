package org.enterprisedomain.classmaker.jobs.codegen;

import org.eclipse.core.resources.IProject;
import org.eclipse.emf.common.util.EList;
import org.enterprisedomain.classmaker.impl.CustomizerImpl;

public class CreateGeneratorCustomizer extends CustomizerImpl {

	@Override
	public Object customize(EList<Object> args) {
		EcoreGenerator generator = new EcoreGenerator((Integer) args.get(0), (Long) args.get(2));
		generator.setProject((IProject) args.get(1));
		return generator;
	}

}
