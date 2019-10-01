#!/usr/bin/env python3

import sys
import numpy as np
from scipy.io.wavfile import write
from note_map import note_map
import parsers as ps
import player as play


def main():
    command_line_arguments = ps.receive_input_arguments()
    adsr_file_string = command_line_arguments[0]
    output_file_name = command_line_arguments[len(command_line_arguments)-1]

    # Sample rate, notes to be played 
    sample_rate_in_hertz = int(command_line_arguments[1])
    number_of_notes, partiture_of_notes = ps.receive_and_parse_partiture_file()
    adsr = ps.receive_and_parse_adsr_file(adsr_file_string)
    # print("*** ADSR: {}\n*** Partiture: {}\n".format(adsr, partiture_of_notes))
    # print('Sample rate in hertz: {}'.format(sample_rate_in_hertz))
    song = play.record_song(adsr, sample_rate_in_hertz, partiture_of_notes)
    # print('Song info:\nMean {}; StdDev {}; Max {}; Min {}'.format(np.mean(song), np.std(song), np.max(song), np.min(song)))
    write(output_file_name, sample_rate_in_hertz*2, song)
    

if __name__ == '__main__':
    main()