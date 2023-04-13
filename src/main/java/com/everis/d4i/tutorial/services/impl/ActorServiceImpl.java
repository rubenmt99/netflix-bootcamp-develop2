package com.everis.d4i.tutorial.services.impl;

import com.everis.d4i.tutorial.entities.Actor;
import com.everis.d4i.tutorial.entities.Chapter;
import com.everis.d4i.tutorial.entities.Season;
import com.everis.d4i.tutorial.entities.TvShow;
import com.everis.d4i.tutorial.exceptions.NetflixException;
import com.everis.d4i.tutorial.exceptions.NotFoundException;
import com.everis.d4i.tutorial.json.ActorRest;
import com.everis.d4i.tutorial.json.ChapterRest;
import com.everis.d4i.tutorial.json.CustomActorRest;
import com.everis.d4i.tutorial.json.TvShowRest;
import com.everis.d4i.tutorial.repositories.ActorRepository;
import com.everis.d4i.tutorial.repositories.ChapterRepository;
import com.everis.d4i.tutorial.repositories.SeasonRepository;
import com.everis.d4i.tutorial.repositories.TvShowRepository;
import com.everis.d4i.tutorial.services.ActorService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.math.BigInteger;
import java.time.Year;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ActorServiceImpl implements ActorService {


    @Autowired
    private ActorRepository actorRepository;

    @Autowired
    private TvShowRepository tvShowRepository;

    @Autowired
    private ChapterRepository chapterRepository;

    @Autowired
    private SeasonRepository seasonRepository;

    private ModelMapper modelMapper = new ModelMapper();

    @Override
    public List<ActorRest> getActorsByChapter(Long chapterId) throws NetflixException {
        try{
            return actorRepository.actorsByChapter(chapterId).stream()
                    .map(actors -> modelMapper.map(actors, ActorRest.class)).collect(Collectors.toList());
        }catch (EntityNotFoundException entityNotFoundException){
            throw new NotFoundException(entityNotFoundException.getMessage());
        }
    }

    @Override
    public List<ActorRest> getAllActors() throws NetflixException {
            return actorRepository.findAll().stream().map(actor -> modelMapper.map(actor, ActorRest.class)).collect(Collectors.toList());

    }

    @Override
    public ActorRest getActorById(Long id) throws NetflixException {
        try {
            return modelMapper.map(actorRepository.findById(id).orElse(null), ActorRest.class);
        } catch (EntityNotFoundException entityNotFoundException) {
            throw new NotFoundException(entityNotFoundException.getMessage());
        }
    }

    @Override
    public ActorRest createActor(Actor actor) throws NetflixException {
        try{
            return modelMapper.map(actorRepository.save(actor), ActorRest.class);
        }catch (Exception e){
            throw new NotFoundException(e.getMessage());
        }
    }

    @Override
    public void deleteActor(Long idActor) throws NetflixException {
        Actor tvShow = actorRepository.findById(idActor).orElse(null);
        if(tvShow != null){
            try {
                actorRepository.deleteById(idActor);
            }catch (Exception e){
                throw new NotFoundException(e.getMessage());
            }
        }
    }

    @Override
    public ActorRest updateActor(Actor actor) throws NetflixException {
        Actor actor1 = actorRepository.findById(actor.getId()).orElse(null);
        if (actor1 == null){
            return null;
        }

        actor1.setBio(actor.getBio());
        actor1.setGender(actor.getGender());
        actor1.setNationality(actor.getNationality());
        actor1.setName(actor.getName());
        actor1.setBornDate(actor.getBornDate());

        actorRepository.save(actor1);
        return modelMapper.map(actor1,ActorRest.class);
    }

    @Override
    public CustomActorRest customActorSearch(Long id) throws NetflixException {
        // Llama al método que realiza una consulta personalizada en la base de datos
        List<Object[]> results = actorRepository.customActorSearch(id);

        // Inicializa una instancia de CustomActorRest
        CustomActorRest customActorRest = new CustomActorRest();

        // Inicializa una lista vacía de TvShowRest
        List<TvShowRest> tvShowRestList = new ArrayList<>();

        // Inicializa una instancia de ActorRest
        ActorRest actorRest = new ActorRest();

        // Recorre los resultados obtenidos de la consulta personalizada
        for (Object[] result : results) {
            // Obtiene los valores correspondientes a cada atributo del resultado
            BigInteger numero = (BigInteger) result[0];
            BigInteger numero2 = (BigInteger) result[7];
            BigInteger numero3 = (BigInteger) result[11];

            // Convierte el valor short a Year
            Short shortValue = (Short) result[6];
            Integer yearInt = (int) shortValue.intValue();
            Year yearYear = Year.of(yearInt);

            // Crea una instancia de TvShowRest con los valores correspondientes
            TvShowRest tvShowRest = new TvShowRest(numero.longValue(), (String) result[1], (String) result[5],(String) result[3], yearYear,(byte) result[4], (String) result[2]);

            // Busca en la base de datos el TvShow correspondiente al resultado
            TvShow tvShow = tvShowRepository.findById(numero.longValue()).orElse(null);

            // Busca en la base de datos el Chapter correspondiente al resultado
            Chapter chapter = chapterRepository.findById(numero2.longValue()).orElse(null);

            // Si se encontraron tanto el TvShow como el Chapter
            if (tvShow != null && chapter != null){
                // Verifica si ya se agregó el TvShow a la lista de TvShowRest
                boolean alreadyExists = tvShowRestList.contains(tvShowRest);

                // Si el TvShow no está en la lista, lo agrega
                if (!alreadyExists){
                    tvShowRestList.add(tvShowRest);
                }

                // Busca el TvShowRest correspondiente al TvShow en la lista
                for (TvShowRest tv : tvShowRestList) {
                    if (tv.getId().equals(tvShowRest.getId())) {
                        // Si el TvShowRest no tiene una lista de ChapterRest, la inicializa
                        if (tv.getChapterRest() == null) {
                            tv.setChapterRest(new ArrayList<>());
                        }

                        // Crea una instancia de ChapterRest con los valores correspondientes
                        ChapterRest chapterRest = new ChapterRest(numero2.longValue(), (short) result[10], (String) result[8], (short) result[9]);

                        // Si el ChapterRest no está en la lista de ChapterRest del TvShowRest, lo agrega
                        if (!tv.getChapterRest().contains(chapterRest)) {
                            tv.getChapterRest().add(chapterRest);
                        }
                        break;
                    }
                }
            }

            // Asigna los valores correspondientes a ActorRest
            actorRest.setId(numero3.longValue());
            actorRest.setName((String) result[12]);
            actorRest.setBornDate((Date) result[14]);
            actorRest.setNationality((String) result[16]);
            actorRest.setGender((String) result[15]);
            actorRest.setBio((String) result[13]);
        }

        customActorRest.setActorRest(actorRest);
        customActorRest.setTvShowRest(tvShowRestList);

        return customActorRest;
    }


}
