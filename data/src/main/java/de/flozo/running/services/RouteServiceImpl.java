package de.flozo.running.services;

import de.flozo.running.exceptions.NotFoundException;
import de.flozo.running.model.Route;
import de.flozo.running.repositories.RouteRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class RouteServiceImpl implements RouteService {

    private final RouteRepository routeRepository;

    public RouteServiceImpl(RouteRepository routeRepository) {
        this.routeRepository = routeRepository;
    }

    @Override
    public Set<Route> findAll() {
        Set<Route> routes = new HashSet<>();
        routeRepository.findAll().iterator().forEachRemaining(routes::add);
        return routes;
    }

    @Override
    public Route findById(Long aLong) {
        Optional<Route> routeOptional = routeRepository.findById(aLong);
        if (routeOptional.isEmpty()) {
            throw new NotFoundException("Route not found! Id value " + aLong + " not present!");
        }
        return routeOptional.get();
    }

    @Override
    public Route save(Route route) {
        return routeRepository.save(route);
    }

    @Override
    public void delete(Route route) {
        routeRepository.delete(route);
    }

    @Override
    public void deleteById(Long aLong) {
        routeRepository.deleteById(aLong);
    }

    @Override
    public Route findByName(String name) {
        return routeRepository.findByName(name);
    }
}
