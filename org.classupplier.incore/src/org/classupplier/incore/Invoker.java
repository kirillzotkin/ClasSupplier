package org.classupplier.incore;

import javax.inject.Inject;

import org.classupplier.ClasSupplier;
import org.eclipse.e4.core.contexts.ContextInjectionFactory;
import org.eclipse.e4.core.contexts.EclipseContextFactory;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.EcorePackage;

public class Invoker implements Runnable {

	@Inject
	private ClasSupplier supplier;

	public Invoker() {
		prepare();
	}

	private void prepare() {
		IEclipseContext context = EclipseContextFactory
				.getServiceContext(Activator.getContext());
		ContextInjectionFactory.inject(this, context);
	}

	public void run() {
		for (Object o : EPackage.Registry.INSTANCE.values()) {
			if (o instanceof EPackage.Descriptor)
				System.out.println(((EPackage.Descriptor) o).getEPackage()
						.toString());
		}
		EPackage p = EcoreFactory.eINSTANCE.createEPackage();
		// one letter 'h' doesn't work
		p.setName("ho");
		p.setNsPrefix("ho");
		p.setNsURI("http://ho/0.1");
		EClass c = EcoreFactory.eINSTANCE.createEClass();
		c.setName("Obj");
		EAttribute a = EcoreFactory.eINSTANCE.createEAttribute();
		a.setName("p");
		a.setEType(EcorePackage.Literals.ESTRING);
		c.getEStructuralFeatures().add(a);
		p.getEClassifiers().add(c);

		EPackage ep = supplier.supply(p);
		EClass e = (EClass) ep.getEClassifier(c.getName());
		System.out.println(e.getName());
		EObject o = ep.getEFactoryInstance().create(e);
		EAttribute ea = (EAttribute) e.getEStructuralFeature(a.getName());
		o.eSet(ea, "Lorem ipsum");
		Object v = o.eGet(ea);
		System.out.println(v);
	}
}
