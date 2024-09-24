package be.bstorm.demowebappjakartaee.repositories.impls;

import be.bstorm.demowebappjakartaee.repositories.BaseRepository;
import be.bstorm.demowebappjakartaee.utils.EntityManagerFactoryProvider;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Id;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public abstract class BaseRepositoryImpl<TEntity, TId> implements BaseRepository<TEntity, TId> {

    protected final EntityManagerFactory emf;
    private final Class<TEntity> entityClass;

    public BaseRepositoryImpl(Class<TEntity> entityClass) {
        this.entityClass = entityClass;
        this.emf = EntityManagerFactoryProvider.getEntityManagerFactory();
    }

    @Override
    public TEntity save(TEntity entity) {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            em.merge(entity);
            em.getTransaction().commit();
            return entity;
        }
    }

    @Override
    public Optional<TEntity> findById(TId tId) {
        try (EntityManager em = emf.createEntityManager()) {
            TEntity entity = em.find(this.entityClass, tId);
            if (entity == null) {
                throw new RuntimeException("entity witd id : " + tId.toString() + " not found");
            }
            return Optional.of(entity);
        }
    }

    @Override
    public List<TEntity> findAll() {
        try (EntityManager em = emf.createEntityManager()) {
            return em.createQuery("SELECT e FROM " + this.entityClass.getSimpleName() + " e", entityClass)
                    .getResultList();
        }
    }

    @Override
    public void update(TEntity entity) {
        try (EntityManager em = emf.createEntityManager()) {
            TId id = null;
            Field[] fields = entityClass.getDeclaredFields();
            Field field = Arrays.stream(fields)
                    .filter(f -> f.isAnnotationPresent(Id.class))
                    .findFirst().orElseThrow(() -> new IllegalArgumentException("No field annotated with @Id"));
            field.setAccessible(true);
            id = (TId) field.get(entity);

            em.getTransaction().begin();
            TEntity entity1 = em.find(this.entityClass, id);
            if (entity1 == null) {
                throw new RuntimeException();
            }
            em.merge(entity);
            em.getTransaction().commit();
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(TId tId) {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            TEntity entity = em.find(this.entityClass, tId);
            if (entity == null) {
                throw new RuntimeException("entity witd id : " + tId.toString() + " not found");
            }
            em.remove(entity);
            em.getTransaction().commit();
        }
    }
}