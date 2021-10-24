package fr.zbar.kata.bowling.ext;

import fr.zbar.kata.bowling.model.frame.Frame;

import java.util.List;

@FunctionalInterface
public interface GameParser {

    List<Frame> frames(String gameForm);
}
