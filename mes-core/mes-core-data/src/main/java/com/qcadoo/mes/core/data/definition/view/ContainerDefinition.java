package com.qcadoo.mes.core.data.definition.view;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import com.qcadoo.mes.core.data.beans.Entity;

public abstract class ContainerDefinition extends ComponentDefinition {

    private final Map<String, ComponentDefinition> components = new LinkedHashMap<String, ComponentDefinition>();

    public ContainerDefinition(final String name, final ContainerDefinition parentContainer, final String fieldPath,
            final String sourceFieldPath) {
        super(name, parentContainer, fieldPath, sourceFieldPath);
    }

    public Map<String, ComponentDefinition> getComponents() {
        return components;
    }

    public void addComponent(final ComponentDefinition component) {
        components.put(component.getName(), component);
    }

    @Override
    public Object getValue(final Entity entity, final Map<String, Object> selectableValues, final Object viewEntity) {
        Map<String, Object> result = new HashMap<String, Object>();
        for (ComponentDefinition component : components.values()) {
            Object value = component.getValue(entity, selectableValues, viewEntity);
            if (value != null) {
                result.put(component.getName(), value);
            }
        }
        return result;
    }

    @Override
    public boolean isContainer() {
        return true;
    }

}
