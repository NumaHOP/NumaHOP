package fr.progilone.pgcn.web.rest.document.conditionreport;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import fr.progilone.pgcn.domain.document.DocUnit;
import fr.progilone.pgcn.domain.document.conditionreport.ConditionReport;
import fr.progilone.pgcn.domain.workflow.DocUnitState;
import fr.progilone.pgcn.domain.workflow.DocUnitWorkflow;
import fr.progilone.pgcn.domain.workflow.WorkflowStateStatus;
import fr.progilone.pgcn.domain.workflow.state.ValidationConstatEtatState;
import fr.progilone.pgcn.service.document.conditionreport.ConditionReportExportService;
import fr.progilone.pgcn.service.document.conditionreport.ConditionReportImportService;
import fr.progilone.pgcn.service.document.conditionreport.ConditionReportService;
import fr.progilone.pgcn.service.es.EsConditionReportService;
import fr.progilone.pgcn.util.TestUtil;
import fr.progilone.pgcn.web.util.AccessHelper;
import fr.progilone.pgcn.web.util.LibraryAccesssHelper;
import fr.progilone.pgcn.web.util.WorkflowAccessHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ExtendWith(MockitoExtension.class)
public class ConditionReportControllerTest {

    @Mock
    private AccessHelper accessHelper;
    @Mock
    private LibraryAccesssHelper libraryAccesssHelper;
    @Mock
    private WorkflowAccessHelper workflowAccessHelper;
    @Mock
    private ConditionReportExportService conditionReportExchangeService;
    @Mock
    private ConditionReportImportService conditionReportImportService;
    @Mock
    private ConditionReportService conditionReportService;
    @Mock
    private EsConditionReportService esConditionReportService;

    private MockMvc restMockMvc;

    @BeforeEach
    public void setUp() {
        final ConditionReportController controller = new ConditionReportController(accessHelper,
                                                                                   libraryAccesssHelper,
                                                                                   workflowAccessHelper,
                                                                                   conditionReportExchangeService,
                                                                                   conditionReportImportService,
                                                                                   conditionReportService,
                                                                                   esConditionReportService);
        this.restMockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void testCreate() throws Exception {
        final ConditionReport report = getConditionReport();
        final String docUnitId = report.getDocUnit().getIdentifier();

        when(accessHelper.checkDocUnit(eq(docUnitId))).thenReturn(false, true, true);
        when(conditionReportService.create(docUnitId)).thenReturn(report);

        // 403
        this.restMockMvc.perform(post("/api/rest/condreport").param("docUnit", docUnitId)
                                                             .contentType(MediaType.APPLICATION_JSON)
                                                             .content(TestUtil.convertObjectToJsonBytes(report))).andExpect(status().isForbidden());

        // 201
        this.restMockMvc.perform(post("/api/rest/condreport").param("docUnit", docUnitId)
                                                             .contentType(MediaType.APPLICATION_JSON)
                                                             .content(TestUtil.convertObjectToJsonBytes(report)))
                        .andExpect(status().isCreated())
                        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                        .andExpect(jsonPath("identifier").value(report.getIdentifier()));
    }

    @Test
    public void testDelete() throws Exception {
        final ConditionReport report = getConditionReport();
        final String docUnitId = report.getDocUnit().getIdentifier();

        when(conditionReportService.findDocUnitByIdentifier(report.getIdentifier())).thenReturn(report.getDocUnit());
        when(accessHelper.checkDocUnit(eq(docUnitId))).thenReturn(false, true);
        when(workflowAccessHelper.canConstatBeDeleted(eq(docUnitId))).thenReturn(false, true);

        // 403
        this.restMockMvc.perform(delete("/api/rest/condreport/{id}", report.getIdentifier()).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isForbidden());
        verify(conditionReportService, never()).delete(report.getIdentifier());

        // 403 workflow
        addWorkflow(report.getDocUnit(), true);
        this.restMockMvc.perform(delete("/api/rest/condreport/{id}", report.getIdentifier()).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isForbidden());
        verify(conditionReportService, never()).delete(report.getIdentifier());
        addWorkflow(report.getDocUnit(), false);

        // 200
        this.restMockMvc.perform(delete("/api/rest/condreport/{id}", report.getIdentifier()).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
        verify(conditionReportService).delete(report.getIdentifier());
    }

    @Test
    public void testFindByIdentifier() throws Exception {
        final ConditionReport report = getConditionReport();
        final String docUnitId = report.getDocUnit().getIdentifier();

        when(conditionReportService.findByIdentifier(report.getIdentifier())).thenReturn(report);
        when(accessHelper.checkDocUnit(eq(docUnitId))).thenReturn(false, true);

        // 403
        this.restMockMvc.perform(get("/api/rest/condreport/{identifier}", report.getIdentifier()).accept(MediaType.APPLICATION_JSON)).andExpect(status().isForbidden());

        // 200
        this.restMockMvc.perform(get("/api/rest/condreport/{identifier}", report.getIdentifier()).accept(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk())
                        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                        .andExpect(jsonPath("identifier").value(report.getIdentifier()));
    }

    @Test
    public void findByDocUnit() throws Exception {
        final ConditionReport report = getConditionReport();
        final String docUnitId = report.getDocUnit().getIdentifier();

        when(accessHelper.checkDocUnit(eq(docUnitId))).thenReturn(false, true);
        when(conditionReportService.findByDocUnit(docUnitId)).thenReturn(report);

        // 403
        this.restMockMvc.perform(get("/api/rest/condreport").param("docUnit", docUnitId).accept(MediaType.APPLICATION_JSON)).andExpect(status().isForbidden());

        // 200
        this.restMockMvc.perform(get("/api/rest/condreport").param("docUnit", docUnitId).accept(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk())
                        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                        .andExpect(jsonPath("identifier").value(report.getIdentifier()));
    }

    @Test
    public void testUpdate() throws Exception {
        // report existant
        final ConditionReport report = getConditionReport();
        final String docUnitId = report.getDocUnit().getIdentifier();

        when(conditionReportService.findDocUnitByIdentifier(report.getIdentifier())).thenReturn(report.getDocUnit());
        when(accessHelper.checkDocUnit(eq(docUnitId))).thenReturn(false, true);
        when(conditionReportService.save(any(ConditionReport.class))).thenReturn(report);

        // 403 sur report existant
        this.restMockMvc.perform(post("/api/rest/condreport/{id}", report.getIdentifier()).contentType(MediaType.APPLICATION_JSON)
                                                                                          .content(TestUtil.convertObjectToJsonBytes(report))).andExpect(status().isForbidden());

        // 200
        this.restMockMvc.perform(post("/api/rest/condreport/{id}", report.getIdentifier()).contentType(MediaType.APPLICATION_JSON)
                                                                                          .content(TestUtil.convertObjectToJsonBytes(report)))
                        .andExpect(status().isOk())
                        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                        .andExpect(jsonPath("identifier").value(report.getIdentifier()));
    }

    private ConditionReport getConditionReport() {
        final DocUnit docUnit = new DocUnit();
        docUnit.setIdentifier("139d6544-d809-4b35-8cf4-51df8cc3ad80");
        final ConditionReport report = new ConditionReport();
        report.setIdentifier("6ad3b23d-2568-4bdc-b2c5-aa33702a52d5");
        report.setDocUnit(docUnit);
        return report;
    }

    private void addWorkflow(final DocUnit doc, final boolean isStateDone) {
        final DocUnitWorkflow workflow = new DocUnitWorkflow();
        final DocUnitState state = new ValidationConstatEtatState();
        if (isStateDone) {
            state.setStatus(WorkflowStateStatus.FINISHED);
        } else {
            state.setStatus(WorkflowStateStatus.PENDING);
        }
        workflow.addState(state);
        doc.setWorkflow(workflow);
    }
}
