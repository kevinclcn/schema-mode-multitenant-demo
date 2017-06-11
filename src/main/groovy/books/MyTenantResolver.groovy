package books

import org.grails.datastore.mapping.multitenancy.AllTenantsResolver
import org.grails.datastore.mapping.multitenancy.TenantResolver
import org.grails.datastore.mapping.multitenancy.exceptions.TenantNotFoundException
import org.springframework.context.annotation.Bean
import org.springframework.web.context.request.RequestAttributes
import org.springframework.web.context.request.RequestContextHolder

/**
 * Created by long on 2017/6/11.
 */
class MyTenantResolver implements TenantResolver, AllTenantsResolver {
    public static final String ATTRIBUTE = "gorm.tenantId"
    /**
     * The attribute name to use
     */
    String attributeName = ATTRIBUTE

    @Override
    Iterable<Serializable> resolveTenantIds() {
        return Arrays.asList("tenant1", "tenant2")
    }

    @Override
    Serializable resolveTenantIdentifier() throws TenantNotFoundException {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes()
        if(requestAttributes != null) {

            def tenantId = requestAttributes.params.x_tenant_id
            if(tenantId instanceof Serializable) {

                if (tenantId == "1") {
                    return 'tenant1'
                } else if (tenantId == "2"){
                    return 'tenant2'
                } else {
                    throw new TenantNotFoundException()
                }

                return (Serializable)tenantId
            }
            else {
                throw new TenantNotFoundException()
            }
        }
        throw new TenantNotFoundException("Tenant could not be resolved outside a web request")
    }
}
