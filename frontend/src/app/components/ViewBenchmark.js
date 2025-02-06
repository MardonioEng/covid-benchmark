'use client';

import { useState, useEffect } from 'react';
import { useRouter } from 'next/navigation';
import axios from 'axios';

const ViewBenchmark = ({ id }) => {
    const router = useRouter();
    const [benchmark, setBenchmark] = useState(null);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);

    useEffect(() => {
        loadBenchmark();
    }, [id]);

    const loadBenchmark = async () => {
        try {
            setLoading(true);
            const response = await axios.get(`/api/benchmarks/${id}`);
            setBenchmark(response.data);
        } catch (err) {
            setError('Erro ao carregar o benchmark: ' + err.message);
        } finally {
            setLoading(false);
        }
    };

    const formatDate = (dateString) => {
        return new Date(dateString).toLocaleDateString('pt-BR');
    };

    if (loading) {
        return (
            <div className="container mt-4">
                <div className="text-center">
                    <div className="spinner-border" role="status">
                        <span className="visually-hidden">Carregando...</span>
                    </div>
                </div>
            </div>
        );
    }

    if (error) {
        return (
            <div className="container mt-4">
                <div className="alert alert-danger" role="alert">
                    {error}
                </div>
            </div>
        );
    }

    if (!benchmark) {
        return (
            <div className="container mt-4">
                <div className="alert alert-info">
                    Benchmark não encontrado
                </div>
            </div>
        );
    }

    return (
        <div className="container mt-4">
            <div className="d-flex justify-content-between align-items-center mb-4">
                <h2>Detalhes do Benchmark</h2>
                <button 
                    className="btn btn-secondary"
                    onClick={() => router.push('/')}
                >
                    Voltar
                </button>
            </div>

            <div className="card">
                <div className="card-body">
                    <h3 className="card-title mb-4">{benchmark.name}</h3>
                    
                    <div className="row mb-4">
                        <div className="col-md-6">
                            <h4>Informações Gerais</h4>
                            <table className="table">
                                <tbody>
                                    <tr>
                                        <th>ID:</th>
                                        <td>{benchmark.id}</td>
                                    </tr>
                                    <tr>
                                        <th>País 1:</th>
                                        <td>{benchmark.country1}</td>
                                    </tr>
                                    <tr>
                                        <th>País 2:</th>
                                        <td>{benchmark.country2}</td>
                                    </tr>
                                    <tr>
                                        <th>Data Início:</th>
                                        <td>{formatDate(benchmark.startDate)}</td>
                                    </tr>
                                    <tr>
                                        <th>Data Fim:</th>
                                        <td>{formatDate(benchmark.endDate)}</td>
                                    </tr>
                                    <tr>
                                        <th>Criado em:</th>
                                        <td>{formatDate(benchmark.createtAt)}</td>
                                    </tr>
                                    <tr>
                                        <th>Atualizado em:</th>
                                        <td>{formatDate(benchmark.updatedAt)}</td>
                                    </tr>
                                </tbody>
                            </table>
                        </div>
                        
                        <div className="col-md-6">
                            <h4>Resultados</h4>
                            {benchmark.result ? (
                                <table className="table">
                                    <tbody>
                                        <tr>
                                            <th>Casos {benchmark.country1}:</th>
                                            <td>{benchmark.result.casesCountry1}</td>
                                        </tr>
                                        <tr>
                                            <th>Casos {benchmark.country2}:</th>
                                            <td>{benchmark.result.casesCountry2}</td>
                                        </tr>
                                        <tr>
                                            <th>Mortes {benchmark.country1}:</th>
                                            <td>{benchmark.result.deathsCountry1}</td>
                                        </tr>
                                        <tr>
                                            <th>Mortes {benchmark.country2}:</th>
                                            <td>{benchmark.result.deathsCountry2}</td>
                                        </tr>
                                    </tbody>
                                </table>
                            ) : (
                                <p>Nenhum resultado disponível</p>
                            )}
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );
};

export default ViewBenchmark;