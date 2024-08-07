import { Select, Stack } from "@chakra-ui/react";

interface TypeExerciseProps {
    setParameter1: React.Dispatch<React.SetStateAction<string>>;
    setParameter2: React.Dispatch<React.SetStateAction<string>>;
}

const TypeExercise : React.FC<TypeExerciseProps> = ({setParameter1, setParameter2}) => {

    const handleChange = (e: React.ChangeEvent<HTMLSelectElement>) => {
        setParameter2(e.target.value);
    };

    return (
        <Stack spacing={5} direction="row">
            <Select
                placeholder='Type of Exercise'
                borderColor='blue.500'
                onChange={handleChange}
            >
                <option value='cardio'> Cardio </option>
                <option value='olympic_weightlifting'> Olympic Weighlifting </option>
                <option value='plyometrics'> Plyometrics </option>
                <option value='powerlifting'> Powerlifting</option>
                <option value='strength'> Strength </option>
                <option value='stretching'> Stretching </option>
                <option value='strongman'> Strongman </option>
            </Select>
        </Stack>
    )
}

export default TypeExercise;