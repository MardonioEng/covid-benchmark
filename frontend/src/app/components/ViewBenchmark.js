'use client';

import { useState, useEffect } from 'react';
import { useRouter } from 'next/navigation';
import axios from 'axios';
import { ArrowLeft } from 'lucide-react';

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
                <button 
                    className="btn btn-link text-decoration-none"
                    onClick={() => router.push('/')}
                >
                    <ArrowLeft className="me-2" size={20} />
                    Voltar
                </button>
            </div>

            <div className="row">
                <div className="col-md-8">
                    <div className="card mb-4">
                        <div className="card-body">
                            <h3 className="card-title mb-4">{benchmark.name}</h3>
                            <div className="row">
                                <div className="col-md-6">
                                    <div className="mb-3">
                                        <small className="text-muted">ID</small>
                                        <p className="mb-0">{benchmark.id}</p>
                                    </div>
                                    <div className="mb-3">
                                        <small className="text-muted">País 1</small>
                                        <p className="mb-0">{benchmark.country1}</p>
                                    </div>
                                    <div className="mb-3">
                                        <small className="text-muted">País 2</small>
                                        <p className="mb-0">{benchmark.country2}</p>
                                    </div>
                                </div>
                                <div className="col-md-6">
                                    <div className="mb-3">
                                        <small className="text-muted">Data Início</small>
                                        <p className="mb-0">{new Date(benchmark.startDate).toLocaleDateString('pt-BR')}</p>
                                    </div>
                                    <div className="mb-3">
                                        <small className="text-muted">Data Fim</small>
                                        <p className="mb-0">{new Date(benchmark.endDate).toLocaleDateString('pt-BR')}</p>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                    {benchmark.result && (
                        <div className="card">
                            <div className="card-body">
                                <h4 className="card-title mb-4">Resultados</h4>
                                <div className="row">
                                    <div className="col-md-6">
                                        <div className="card bg-light mb-3">
                                            <div className="card-body">
                                                <h5 className="card-title">{benchmark.country1}</h5>
                                                <div className="mb-2">
                                                    <small className="text-muted">Casos</small>
                                                    <p className="h4 mb-0">{benchmark.result.casesCountry1.toLocaleString()}</p>
                                                </div>
                                                <div>
                                                    <small className="text-muted">Mortes</small>
                                                    <p className="h4 mb-0">{benchmark.result.deathsCountry1.toLocaleString()}</p>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div className="col-md-6">
                                        <div className="card bg-light">
                                            <div className="card-body">
                                                <h5 className="card-title">{benchmark.country2}</h5>
                                                <div className="mb-2">
                                                    <small className="text-muted">Casos</small>
                                                    <p className="h4 mb-0">{benchmark.result.casesCountry2.toLocaleString()}</p>
                                                </div>
                                                <div>
                                                    <small className="text-muted">Mortes</small>
                                                    <p className="h4 mb-0">{benchmark.result.deathsCountry2.toLocaleString()}</p>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    )}
                </div>

                <div className="col-md-4">
                    <div className="card">
                        <div className="card-body">
                            <h4 className="card-title mb-4">Informações Adicionais</h4>
                            <div className="mb-3">
                                <small className="text-muted">Criado em</small>
                                <p className="mb-0">{new Date(benchmark.createtAt).toLocaleDateString('pt-BR')}</p>
                            </div>
                            <div className="mb-3">
                                <small className="text-muted">Atualizado em</small>
                                <p className="mb-0">{new Date(benchmark.updatedAt).toLocaleDateString('pt-BR')}</p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );
};

export default ViewBenchmark;