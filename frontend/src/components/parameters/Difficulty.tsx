import { Select, Stack } from "@chakra-ui/react";

interface DifficultyProps {
    setParameter1: React.Dispatch<React.SetStateAction<string>>;
    setParameter2: React.Dispatch<React.SetStateAction<string>>;
}

const Difficulty : React.FC<DifficultyProps> = ({setParameter1, setParameter2}) => {

    const handleChange = (e: React.ChangeEvent<HTMLSelectElement>) => {
        setParameter2(e.target.value);
    };

    return (
        <Stack spacing={5} direction="row">
            <Select
                placeholder='Level of difficulty'
                borderColor='blue.500'
                onChange={handleChange}
            >
                <option value='beginner'> Beginner </option>
                <option value='intermediate'> Intermediate </option>
                <option value='expert'> Expert </option>
            </Select>
        </Stack>
    )
}

export default Difficulty;