
import sys


def receive_input_arguments():
    sys.argv.pop(0)
    assert len(sys.argv) == 3, "Arguments: adsr file, sample frequency and output file (wav). Received wrong number of arguments."
    return sys.argv


def receive_and_parse_adsr_file(adsr_file_string):
    file = open(adsr_file_string, "r")
    partiture_file = file.readlines()
    parsed_file = list(map(lambda s: s.replace('\n', '').split(), partiture_file))
    for i in range(len(parsed_file)): # todo: improve
        for j in range(len(parsed_file[0])):
            parsed_file[i][j] = float(parsed_file[i][j])
    return parsed_file


def receive_and_parse_partiture_file():
    partiture_file = sys.stdin.readlines()
    parsed_file = list(map(lambda s: s.replace('\n', '').split(), partiture_file))
    number_of_notes = parsed_file[0][0]
    parsed_file.remove(parsed_file[0])
    n = len(parsed_file)
    for i in range(n):  # todo: improve
        m = len(parsed_file[i])
        for j in range(m):
            if j == m-1:
                parsed_file[i][j] = int(parsed_file[i][j])

    return int(number_of_notes), parsed_file
