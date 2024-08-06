import { useState } from 'react';
import { useRouter } from 'next/router';
import {
  Box,
  Button,
  FormControl,
  FormLabel,
  Input,
  Stack,
  Heading,
  Text,
  useColorModeValue,
  useToast,
  InputRightElement,
  InputGroup
} from '@chakra-ui/react';
import Cookies from 'js-cookie';
import { authenticate } from '../../services/authService';
import React from 'react';
import Link from 'next/link';

const Login = () => {
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const router = useRouter();
  const toast = useToast();
  const [loading, setLoading] = useState(false);
  const [show, setShow] = React.useState(false)
  const handleClick = () => setShow(!show)

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    setLoading(true);
    try {
      const data = await authenticate(email, password);
      Cookies.set('token', data.token, { expires: 1, secure: true, sameSite: 'strict' });
      toast({
        title: 'Login realizado com sucesso.',
        status: 'success',
        duration: 5000,
        isClosable: true,
      });
      router.push('/dashboard');
    } catch (error: any) {
      toast({
        title: 'Falha na autenticação.',
        description: error.message,
        status: 'error',
        duration: 5000,
        isClosable: true,
      });
    } finally {
      setLoading(false);
    }
  };

  return (
    <Box
      minH="100vh"
      display="flex"
      alignItems="center"
      justifyContent="center"
      bg={useColorModeValue('gray.50', 'gray.800')}
      py={12}
      px={6}
    >
      <Stack spacing={8} mx="auto" maxW="lg" width="100%" py={12} px={6}>
        <Stack align="center">
          <Heading fontSize="4xl">GYM API REST</Heading>
          <Text fontSize="lg" color="gray.600">
            Realize o login com os dados abaixo:
          </Text>
        </Stack>
        <Box
          rounded="lg"
          bg={useColorModeValue('white', 'gray.700')}
          boxShadow="lg"
          p={8}
          width="100%" 
          maxWidth="600vh"
        >
          <form onSubmit={handleSubmit}>
            <Stack spacing={4}>

              <FormControl id="email">
                <FormLabel>Email</FormLabel>
                <Input
                  type="email"
                  value={email}
                  onChange={(e) => setEmail(e.target.value)}
                  required
                  placeholder='Digite seu email'
                />
              </FormControl>
              <FormControl id="password">

                <FormLabel>Senha</FormLabel>
                <InputGroup size='md'>
                  <Input
                    type={show ? 'text' : 'password'}
                    value={password}
                    onChange={(e) => setPassword(e.target.value)}
                    required
                    placeholder='Digite sua senha'
                  />
                  <InputRightElement width='4.5rem'>
                    <Button h='2.5rem' size='sm' onClick={handleClick}>
                      {show ? 'Esconder' : 'Mostrar'}
                    </Button>
                  </InputRightElement>
                </InputGroup>

              </FormControl>

              <Stack spacing={10}>
                <Button
                  isLoading={loading}
                  bg="blue.400"
                  color="white"
                  _hover={{
                    bg: 'blue.500',
                  }}
                  type="submit"
                >
                  Entrar
                </Button>
              </Stack>
                
              <Stack spacing={10}>
                <Link href="/register">
                  Não possui cadastro ainda? Clique aqui
                </Link>
              </Stack>
            
            </Stack>
          </form>
        </Box>
      </Stack>
    </Box>
  );
};

export default Login;
