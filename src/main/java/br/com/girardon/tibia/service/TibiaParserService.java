package br.com.girardon.tibia.service;

import br.com.girardon.tibia.utils.ReadLogFileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TibiaParserService {

    private static final Logger logger = LoggerFactory.getLogger(TibiaParserService.class);

    @Autowired
    private HealingService healingService;
    @Autowired
    private DamageTakenService damageTakenService;
    @Autowired
    private ExperienceService experienceGainService;
    @Autowired
    private DropService dropService;

    public TibiaParserService parseLogFile() {
        String logContent = ReadLogFileUtils.readLogFile();
//        List<Integer> healingValues = healingService.findHealingValues(logContent);
//        List<Integer> damageValues = damageTakenService.findDamageTakenValues(logContent);
//        List<Integer> experienceGainValues = experienceGainService.findExperienceGainValues(logContent);
//        Map<String, Integer> damageTakenByMonster = damageTakenService.findDamageTakenByMonster(logContent);
        Map<String, Integer> creatureDrops = dropService.findCreatureDropValues(logContent);

        return null;
    }


}