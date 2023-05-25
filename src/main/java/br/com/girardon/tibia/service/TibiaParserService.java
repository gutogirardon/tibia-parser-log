package br.com.girardon.tibia.service;

import br.com.girardon.tibia.utils.ReadLogFileUtils;
import org.springframework.stereotype.Service;

@Service
public class TibiaParserService {
    public TibiaParserService parseLogFile() {
        String logContent = ReadLogFileUtils.readLogFile();

        return null;
    }
}
