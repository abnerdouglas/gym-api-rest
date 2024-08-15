import { Select, Stack } from "@chakra-ui/react";

interface MuscleProps {
    setParameter1: React.Dispatch<React.SetStateAction<string>>;
    setParameter2: React.Dispatch<React.SetStateAction<string>>;
}

const Muscle: React.FC<MuscleProps> = ({ setParameter1, setParameter2 }) => {

    const handleChange = (e: React.ChangeEvent<HTMLSelectElement>) => {
        setParameter2(e.target.value);
    };

    return (
        <Stack spacing={5} direction="row">
            <Select
                placeholder='Muscle Group'
                borderColor='blue.500'
                onChange={handleChange}
            >
                <option value={'abdominals'}> Abdominals </option>
                <option value={'abductors'}> Abductors </option>
                <option value={'adductors'}> Adductors </option>
                <option value={'biceps'}> Biceps </option>
                <option value={'triceps'}> Triceps </option>
                <option value={'calves'}> Calves </option>
                <option value={'chest'}> Chest </option>
                <option value={'forearms'}> Forearms </option>
                <option value={'glutes'}> Glutes </option>
                <option value={'hamstrings'}> Hamstrings </option>
                <option value={'lats'}> Lats </option>
                <option value={'lower_back'}> Lower Back </option>
                <option value={'middle_back'}> Middle Back </option>
                <option value={'neck'}> Neck </option>
                <option value={'quadriceps'}> Quadriceps </option>
                <option value={'traps'}> Traps </option>
            </Select>
        </Stack>
    )
}

export default Muscle;