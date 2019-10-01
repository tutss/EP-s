
import numpy as np
from tqdm import tqdm
import matplotlib.pyplot as plt
from note_map import note_map


def record_song(adsr, sample_rate, part):
    global max_note_length
    max_note_length = max_length(part)
    music_flow = []
    for i_line in tqdm(range(len(part))):
        notes_in_line = part[i_line]
        time_for_each_note = notes_in_line.pop()
        for note in notes_in_line:
            note_frequency = note_map[note]
            note_sound = play_note(note_frequency, time_for_each_note, sample_rate, adsr)
            for i in range(len(note_sound)):
                music_flow.append(note_sound[i])
    
    return np.asarray(music_flow, dtype=np.float32)

    
def play_note(freq, time_range, sample_rate, adsr):
    note_sound = []    
    time = 0
    for envelope_moment in adsr:
        duration = envelope_moment[0]
        amplitude = envelope_moment[1]
        t = np.linspace(time, ((time_range/max_note_length)*duration)+time, num=sample_rate*2*duration, endpoint=False)
        data = list((np.sin(2 * np.pi * freq * t) * amplitude + 1))
        time = ((time_range/max_note_length)*duration)+time
        data = list(data)
        for i in range(len(data)):
            note_sound.append(data[i])
    # print('Note info:\nMean {}; StdDev {}; Max {}; Min {}'.format(np.mean(note_sound), np.std(note_sound), np.max(note_sound), np.min(note_sound)))
    return np.abs(note_sound)


def max_length(partiture):
    max_value = 0
    for line in partiture:
        time = int(line[len(line) - 1])
        if time > max_value:
            max_value = time
    return max_value
