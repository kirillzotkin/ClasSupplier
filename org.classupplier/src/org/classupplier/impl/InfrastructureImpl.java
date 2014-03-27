/**
 */
package org.classupplier.impl;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.classupplier.Artifact;
import org.classupplier.ClasSupplierPackage;
import org.classupplier.ClasSupplierFactory;
import org.classupplier.Infrastructure;
import org.classupplier.State;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.common.notify.impl.NotificationImpl;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Infrastructure</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.classupplier.impl.InfrastructureImpl#getArtifacts <em>Artifacts</em>}</li>
 *   <li>{@link org.classupplier.impl.InfrastructureImpl#getResourceSet <em>Resource Set</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class InfrastructureImpl extends EObjectImpl implements Infrastructure {

	/**
	 * The cached value of the '{@link #getArtifacts() <em>Artifacts</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getArtifacts()
	 * @generated
	 * @ordered
	 */
	protected EList<Artifact> artifacts;

	/**
	 * The default value of the '{@link #getResourceSet() <em>Resource Set</em>}
	 * ' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getResourceSet()
	 * @generated NOT
	 * @ordered
	 */
	protected static final ResourceSet RESOURCE_SET_EDEFAULT = new ResourceSetImpl();

	/**
	 * The cached value of the '{@link #getResourceSet() <em>Resource Set</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getResourceSet()
	 * @generated
	 * @ordered
	 */
	protected ResourceSet resourceSet = RESOURCE_SET_EDEFAULT;

	protected Map<EPackage, Artifact> prototypeModelsToArtifaсts = new HashMap<EPackage, Artifact>();

	protected Map<EPackage, Artifact> loadedModelsToArtifaсts = new HashMap<EPackage, Artifact>();

	private EAdapterList<Adapter> listeners = new EAdapterList<Adapter>(this);

	private Adapter listeningAdapter = new AdapterImpl() {

		@Override
		public void notifyChanged(Notification msg) {
			if (msg.getFeatureID(Artifact.class) == ClasSupplierPackage.ARTIFACT__STATE
					&& msg.getNewValue() == State.PROTOTYPE)
				notifyListeners(new NotificationImpl(msg.getEventType(), null,
						msg.getNotifier()));
		}

	};

	private Adapter contentsAdapter = new AdapterImpl() {

		@Override
		public void notifyChanged(Notification msg) {
			if (msg.getFeatureID(getClass()) == ClasSupplierPackage.INFRASTRUCTURE__ARTIFACTS) {
				switch (msg.getEventType()) {
				case Notification.ADD:
					notifyEPackageAdd((Artifact) msg.getNewValue());
					((EObject) msg.getNewValue()).eAdapters().add(
							listeningAdapter);
					break;
				case Notification.REMOVE:
					((EObject) msg.getOldValue()).eAdapters().remove(
							listeningAdapter);
					notifyEPackageRemove((Artifact) msg.getOldValue());
					break;
				}
			}
		}

	};

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	protected InfrastructureImpl() {
		super();
		eAdapters().add(contentsAdapter);
		init();
	}

	private void init() {
		IProgressMonitor monitor = new NullProgressMonitor();
		IWorkspace workspace = ResourcesPlugin.getWorkspace();
		for (IProject project : workspace.getRoot().getProjects()) {
			ArtifactImpl artifact = null;
			try {
				if (!project.isOpen())
					project.open(monitor);
				if (project.hasNature(OSGi.NATURE_ID)) {
					artifact = (ArtifactImpl) ClasSupplierFactory.eINSTANCE.createArtifact();
					artifact.setProjectName(project.getName());
					registerArtifact(artifact);
					workspace.run(new Initializer(project, artifact, this),
							monitor);
				}
			} catch (CoreException e) {
				if (artifact != null)
					artifact.setStatus(e.getStatus());
			}
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ClasSupplierPackage.Literals.INFRASTRUCTURE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Artifact> getArtifacts() {
		if (artifacts == null) {
			artifacts = new EObjectContainmentEList<Artifact>(Artifact.class, this, ClasSupplierPackage.INFRASTRUCTURE__ARTIFACTS);
		}
		return artifacts;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public ResourceSet getResourceSet() {
		return resourceSet;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated NOT
	 */
	public void setResourceSet(ResourceSet newResourceSet) {
		ResourceSet oldResourceSet = resourceSet;
		resourceSet = newResourceSet;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ClasSupplierPackage.INFRASTRUCTURE__RESOURCE_SET, oldResourceSet, resourceSet));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public void addRefreshListener(Adapter listener) {
		listeners.add(listener);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public void removeRefreshListener(Adapter listener) {
		listeners.remove(listener);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public void registerArtifact(Artifact artifact) {
		getArtifacts().add(artifact);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public void unregisterArtifact(Artifact artifact) {
		getArtifacts().remove(artifact);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public int containsArtifact(EPackage blueprint) {
		for (EPackage ePackage : loadedModelsToArtifaсts.keySet())
			if (packagesAreEqual(ePackage, blueprint))
				return CONTAINS_LOADED;
		for (EPackage ePackage : prototypeModelsToArtifaсts.keySet())
			if (packagesAreEqual(ePackage, blueprint))
				return CONTAINS_PROTOTYPE;
		return DOESNT_CONTAIN;
	}

	private boolean packagesAreEqual(EPackage first, EPackage second) {
		return first.getNsURI().equals(second.getNsURI())
				|| first.getName().equals(second.getName());
	}

	/**
	 * a <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public Artifact getArtifact(String projectName) {
		for (Artifact artifact : getArtifacts()) {
			if (artifact.getProjectName() != null
					&& artifact.getProjectName().equals(projectName))
				return artifact;
		}
		return null;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public Artifact getArtifact(EPackage ePackage) {
		switch (containsArtifact(ePackage)) {
		case CONTAINS_LOADED:
			for (EPackage loaded : loadedModelsToArtifaсts.keySet())
				if (packagesAreEqual(loaded, ePackage))
					return loadedModelsToArtifaсts.get(loaded);
		case CONTAINS_PROTOTYPE:
			for (EPackage prototype : prototypeModelsToArtifaсts.keySet())
				if (packagesAreEqual(prototype, ePackage))
					return prototypeModelsToArtifaсts.get(prototype);
		case DOESNT_CONTAIN:
		default:
			return null;
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public void save(IProgressMonitor monitor) {
		try {
			ResourcesPlugin.getWorkspace().save(true, monitor);
		} catch (CoreException e) {
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public Artifact createArtifact(EPackage blueprint) {
		ArtifactImpl result = (ArtifactImpl) ClasSupplierFactory.eINSTANCE.createArtifact();
		result.setName(blueprint.getName());
		result.setPrototypeEPackage(blueprint);
		registerArtifact(result);
		return result;
	}

	private void notifyListeners(Notification notification) {
		for (Adapter listener : listeners)
			listener.notifyChanged(notification);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated)
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd,
			int featureID, NotificationChain msgs) {
		switch (featureID) {
		case ClasSupplierPackage.INFRASTRUCTURE__ARTIFACTS:
			return ((InternalEList<?>) getArtifacts()).basicRemove(otherEnd,
					msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ClasSupplierPackage.INFRASTRUCTURE__ARTIFACTS:
				return getArtifacts();
			case ClasSupplierPackage.INFRASTRUCTURE__RESOURCE_SET:
				return getResourceSet();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated NOT
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case ClasSupplierPackage.INFRASTRUCTURE__ARTIFACTS:
				getArtifacts().clear();
				getArtifacts().addAll((Collection<? extends Artifact>)newValue);
				return;
			case ClasSupplierPackage.INFRASTRUCTURE__RESOURCE_SET:
				setResourceSet((ResourceSet)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case ClasSupplierPackage.INFRASTRUCTURE__ARTIFACTS:
				getArtifacts().clear();
				return;
			case ClasSupplierPackage.INFRASTRUCTURE__RESOURCE_SET:
				setResourceSet(RESOURCE_SET_EDEFAULT);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case ClasSupplierPackage.INFRASTRUCTURE__ARTIFACTS:
				return artifacts != null && !artifacts.isEmpty();
			case ClasSupplierPackage.INFRASTRUCTURE__RESOURCE_SET:
				return RESOURCE_SET_EDEFAULT == null ? resourceSet != null : !RESOURCE_SET_EDEFAULT.equals(resourceSet);
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (resourceSet: ");
		result.append(resourceSet);
		result.append(')');
		return result.toString();
	}

	protected void notifyEPackageAdd(Artifact artifact) {
		if (artifact
				.eIsSet(ClasSupplierPackage.Literals.ARTIFACT__PROTOTYPE_EPACKAGE))
			prototypeModelsToArtifaсts.put(artifact.getPrototypeEPackage(),
					artifact);
		if (artifact
				.eIsSet(ClasSupplierPackage.Literals.ARTIFACT__LOADED_EPACKAGE))
			loadedModelsToArtifaсts.put(artifact.getLoadedEPackage(), artifact);
	}

	protected void notifyEPackageRemove(Artifact artifact) {
		if (artifact
				.eIsSet(ClasSupplierPackage.Literals.ARTIFACT__PROTOTYPE_EPACKAGE))
			prototypeModelsToArtifaсts.remove(artifact.getPrototypeEPackage());
		if (artifact
				.eIsSet(ClasSupplierPackage.Literals.ARTIFACT__LOADED_EPACKAGE))
			loadedModelsToArtifaсts.remove(artifact.getLoadedEPackage());
	}

} // InfrastructureImpl