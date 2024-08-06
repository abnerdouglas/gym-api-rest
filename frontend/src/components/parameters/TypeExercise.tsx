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
                placeholder='Tipo de Exercício'
                borderColor='blue.500'
                onChange={handleChange}
            >
                <option value='cardio'> Cardio </option>
                <option value='olympic_weightlifting'> Levantamento de Peso Olímpico </option>
                <option value='plyometrics'> Pilometria </option>
                <option value='powerlifting'> Levantamento de Peso </option>
                <option value='strength'> Força </option>
                <option value='stretching'> Alongamento </option>
                <option value='strongman'> Strongman </option>
            </Select>
        </Stack>
    )
}

export default TypeExercise;